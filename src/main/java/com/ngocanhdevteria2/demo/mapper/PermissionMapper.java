package com.ngocanhdevteria2.demo.mapper;

import org.mapstruct.Mapper;

import com.ngocanhdevteria2.demo.dto.request.PermissionRequest;
import com.ngocanhdevteria2.demo.dto.response.PermissionResponse;
import com.ngocanhdevteria2.demo.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest req);

    PermissionResponse toPermissionResponse(Permission permission);
}
