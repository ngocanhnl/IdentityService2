package com.ngocanhdevteria2.demo.service;


import com.ngocanhdevteria2.demo.dto.request.RoleRequest;
import com.ngocanhdevteria2.demo.dto.response.RoleResponse;
import com.ngocanhdevteria2.demo.entity.Role;
import com.ngocanhdevteria2.demo.mapper.RoleMapper;
import com.ngocanhdevteria2.demo.repository.PermissionRepository;
import com.ngocanhdevteria2.demo.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest req) {
        var role = roleMapper.toRole(req);

        var permissions = permissionRepository.findAllById(req.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }


    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();

        return  roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String id) {
        roleRepository.deleteById(id);
    }
}
