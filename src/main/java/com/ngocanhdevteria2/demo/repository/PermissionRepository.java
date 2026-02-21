package com.ngocanhdevteria2.demo.repository;

import com.ngocanhdevteria2.demo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
