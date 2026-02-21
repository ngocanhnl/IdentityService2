package com.ngocanhdevteria2.demo.service;

import com.ngocanhdevteria2.demo.dto.request.PermissionRequest;
import com.ngocanhdevteria2.demo.dto.response.PermissionResponse;
import com.ngocanhdevteria2.demo.entity.Permission;
import com.ngocanhdevteria2.demo.mapper.PermissionMapper;
import com.ngocanhdevteria2.demo.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> findAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String request) {
        permissionRepository.deleteById(request);
    }


}
