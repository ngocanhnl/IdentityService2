package com.ngocanhdevteria2.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ngocanhdevteria2.demo.dto.request.ApiResponse;
import com.ngocanhdevteria2.demo.dto.request.PermissionRequest;
import com.ngocanhdevteria2.demo.dto.response.PermissionResponse;
import com.ngocanhdevteria2.demo.repository.PermissionRepository;
import com.ngocanhdevteria2.demo.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;
    private final PermissionRepository permissionRepository;

    @PostMapping
    ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permission) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(permission))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.findAll())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    ApiResponse<Void> deletePermission(@PathVariable String permissionId) {
        permissionRepository.deleteById(permissionId);
        return ApiResponse.<Void>builder().build();
    }
}
