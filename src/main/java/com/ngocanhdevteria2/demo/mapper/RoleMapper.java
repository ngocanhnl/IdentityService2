package com.ngocanhdevteria2.demo.mapper;

import com.ngocanhdevteria2.demo.dto.request.PermissionRequest;
import com.ngocanhdevteria2.demo.dto.request.RoleRequest;
import com.ngocanhdevteria2.demo.dto.response.PermissionResponse;
import com.ngocanhdevteria2.demo.dto.response.RoleResponse;
import com.ngocanhdevteria2.demo.entity.Permission;
import com.ngocanhdevteria2.demo.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    //ignore do roleRequest dang la Set<String> con Trong Role la Set<Permission>
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest req);
    RoleResponse toRoleResponse(Role role);
}
