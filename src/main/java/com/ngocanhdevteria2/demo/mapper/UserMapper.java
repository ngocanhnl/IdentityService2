package com.ngocanhdevteria2.demo.mapper;

import com.ngocanhdevteria2.demo.dto.request.UserCreationRequest;
import com.ngocanhdevteria2.demo.dto.request.UserUpdateRequest;
import com.ngocanhdevteria2.demo.dto.response.UserResponse;
import com.ngocanhdevteria2.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest req);
    //@Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest req);
}
