package com.ngocanhdevteria2.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ngocanhdevteria2.demo.dto.request.RoleRequest;
import com.ngocanhdevteria2.demo.dto.response.RoleResponse;
import com.ngocanhdevteria2.demo.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    // ignore do roleRequest dang la Set<String> con Trong Role la Set<Permission>
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest req);

    RoleResponse toRoleResponse(Role role);
}
