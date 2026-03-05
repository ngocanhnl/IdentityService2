package com.ngocanhdevteria2.demo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import com.ngocanhdevteria2.demo.dto.request.UserCreationRequest;
import com.ngocanhdevteria2.demo.dto.response.UserResponse;
import com.ngocanhdevteria2.demo.entity.User;
import com.ngocanhdevteria2.demo.exception.AppException;
import com.ngocanhdevteria2.demo.repository.UserRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate birthday;

    @BeforeEach
    // Khoi tao du lieu truoc khi test
    void initData() {
        birthday = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .username("john123456")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .birthDate(birthday)
                .build();

        userResponse = UserResponse.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .id("jhfsjdfkjdfn")
                .birthDate(birthday)
                .build();

        user = User.builder()
                .username("john123456")
                .firstName("John")
                .lastName("Doe")
                .birthDate(birthday)
                .id("jhfsjdfkjdfn")
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("jhfsjdfkjdfn");
        Assertions.assertThat(response.getUsername()).isEqualTo("john123456");
    }

    @Test
    void createUser_userExitsted_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        // Lay ra ex
        var execption = assertThrows(AppException.class, () -> userService.createUser(request));
        // THEN
        Assertions.assertThat(execption.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "john123456")
    void getMyInfo_valid_success() {
        // GIVEN
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // WHEN
        var response = userService.getMyInfo();

        // THEN
        Assertions.assertThat(response.getUsername()).isEqualTo("john123456");
        Assertions.assertThat(response.getId()).isEqualTo("jhfsjdfkjdfn");
    }

    @Test
    @WithMockUser(username = "john123456")
    void getMyInfo_userNotFound_fail() {
        // GIVEN
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));

        // WHEN
        // Lay ra ex
        var execption = assertThrows(AppException.class, () -> userService.getMyInfo());

        // THEN
        Assertions.assertThat(execption.getErrorCode().getCode()).isEqualTo(1005);
    }
}
