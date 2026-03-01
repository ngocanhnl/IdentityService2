package com.ngocanhdevteria2.demo.controller;

import com.ngocanhdevteria2.demo.dto.request.ApiResponse;
import com.ngocanhdevteria2.demo.dto.request.UserCreationRequest;
import com.ngocanhdevteria2.demo.dto.request.UserUpdateRequest;
import com.ngocanhdevteria2.demo.dto.response.UserResponse;
import com.ngocanhdevteria2.demo.entity.User;
import com.ngocanhdevteria2.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest req) {
        log.info("Controller: Create user");

        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.createUser(req));
        return response;
    }

//    @GetMapping
//    List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {

        //SecurityContextHolder chua thong tin dang nhap hien tai
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> {
            log.info("GrantedAuthority : {}", grantedAuthority.getAuthority());
        });


        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getAllUsers());
        return response;
    }
    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId) {

        return userService.getUserById(userId);
    }

    @PutMapping("{userId}")
    ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest req, @PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(req, userId))
                .build();
    }

    @DeleteMapping("{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }


    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {

        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }


}
