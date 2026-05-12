package com.ngocanhdevteria2.demo.configuration;

import java.util.HashSet;

import com.ngocanhdevteria2.demo.constant.PredefinedRole;
import com.ngocanhdevteria2.demo.entity.Role;
import com.ngocanhdevteria2.demo.repository.RoleRepository;
import lombok.experimental.NonFinal;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ngocanhdevteria2.demo.entity.User;

import com.ngocanhdevteria2.demo.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    // Khoi tao moi khi app start
    @Bean
    // Chi chay khi khong phai la Test, dua vao co dang su dung yaml co com.mysql.cj.jdbc.Driver
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                roleRepository.save(Role.builder()
                                .name(PredefinedRole.USER_ROLE)
                                .description("User Role")
                        .build());
                Role adminRole = roleRepository.save( Role.builder()
                                .name(PredefinedRole.ADMIN_ROLE)
                                .description("Admin Role")
                        .build());

                var roles = new HashSet<Role>();
                roles.add(adminRole);
                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                         .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin");
            }
            log.info("Application initialization completed .....");
        };
    }
}
