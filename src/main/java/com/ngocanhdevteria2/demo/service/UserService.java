package com.ngocanhdevteria2.demo.service;

import com.ngocanhdevteria2.demo.dto.request.UserCreationRequest;
import com.ngocanhdevteria2.demo.dto.request.UserUpdateRequest;
import com.ngocanhdevteria2.demo.dto.response.UserResponse;
import com.ngocanhdevteria2.demo.entity.User;
import com.ngocanhdevteria2.demo.enums.Role;
import com.ngocanhdevteria2.demo.exception.AppException;
import com.ngocanhdevteria2.demo.exception.ErrorCode;
import com.ngocanhdevteria2.demo.mapper.UserMapper;
import com.ngocanhdevteria2.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;


    public UserResponse createUser(UserCreationRequest req){
        if (userRepository.existsByUsername(req.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(req);

        user.setPassword(passwordEncoder.encode(req.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers(){
//        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }
    public UserResponse getUserById(String userId){
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found")));
    }
    public UserResponse updateUser(UserUpdateRequest req, String userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));

        userMapper.updateUser(user, req);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
