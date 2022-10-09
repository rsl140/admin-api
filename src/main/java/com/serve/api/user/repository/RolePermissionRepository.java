package com.serve.api.user.repository;

import com.serve.api.user.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {
}
