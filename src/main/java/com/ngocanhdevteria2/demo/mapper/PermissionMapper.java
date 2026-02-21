package com.ngocanhdevteria2.demo.mapper;

import com.ngocanhdevteria2.demo.dto.request.PermissionRequest;
import com.ngocanhdevteria2.demo.dto.response.PermissionResponse;
import com.ngocanhdevteria2.demo.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest req);
    PermissionResponse toPermissionResponse(Permission permission);
}
