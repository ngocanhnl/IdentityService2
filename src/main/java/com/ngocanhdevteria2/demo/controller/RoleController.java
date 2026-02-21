package com.ngocanhdevteria2.demo.controller;


import com.ngocanhdevteria2.demo.dto.request.ApiResponse;
import com.ngocanhdevteria2.demo.dto.request.PermissionRequest;
import com.ngocanhdevteria2.demo.dto.request.RoleRequest;
import com.ngocanhdevteria2.demo.dto.response.PermissionResponse;
import com.ngocanhdevteria2.demo.dto.response.RoleResponse;
import com.ngocanhdevteria2.demo.repository.PermissionRepository;
import com.ngocanhdevteria2.demo.service.PermissionService;
import com.ngocanhdevteria2.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;


    @PostMapping
    ApiResponse<RoleResponse> createPermission(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }


    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build()
                ;
    }

    @DeleteMapping("/{roleId}")
    ApiResponse<Void> deletePermission(@PathVariable String roleId) {
        roleService.delete(roleId);
        return ApiResponse.<Void>builder().build();
    }
}
