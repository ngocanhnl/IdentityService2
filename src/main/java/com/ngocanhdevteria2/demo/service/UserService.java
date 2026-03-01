package com.ngocanhdevteria2.demo.service;

import com.ngocanhdevteria2.demo.dto.request.UserCreationRequest;
import com.ngocanhdevteria2.demo.dto.request.UserUpdateRequest;
import com.ngocanhdevteria2.demo.dto.response.UserResponse;
import com.ngocanhdevteria2.demo.entity.User;
import com.ngocanhdevteria2.demo.enums.Role;
import com.ngocanhdevteria2.demo.exception.AppException;
import com.ngocanhdevteria2.demo.exception.ErrorCode;
import com.ngocanhdevteria2.demo.mapper.UserMapper;
import com.ngocanhdevteria2.demo.repository.RoleRepository;
import com.ngocanhdevteria2.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    RoleRepository roleRepository;


    public UserResponse createUser(UserCreationRequest req){
        log.info("Serivices: Create user");

        if (userRepository.existsByUsername(req.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(req);

        user.setPassword(passwordEncoder.encode(req.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }
    //@Kiem tra truoc khi ham chay, lay trong token
    //@PreAuthorize("hasRole('ADMIN')") //Mac dinh map voi cac phan co prefix ROLE_ o truoc
    @PreAuthorize("hasAuthority('UPDATE_DATA')") // Map voi permission
    public List<UserResponse> getAllUsers(){
        log.info("Method Get all users");
//        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }


    //@Kiem tra sau khi ham chay, lay trong token
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String userId){
        log.info("Method Get user by id ");
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found")));
    }


    public UserResponse updateUser(UserUpdateRequest req, String userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));

        userMapper.updateUser(user, req);

        user.setPassword(passwordEncoder.encode(req.getPassword()));

        var roles = roleRepository.findAllById(req.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public UserResponse getMyInfo(){
         var context = SecurityContextHolder.getContext();
         String name = context.getAuthentication().getName();

         User user = userRepository.findByUsername(name).orElseThrow( () -> new AppException(ErrorCode.USER_NOT_EXISTS));
        return userMapper.toUserResponse(user);
    }
}
