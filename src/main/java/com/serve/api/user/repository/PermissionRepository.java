package com.serve.api.user.repository;

import com.serve.api.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
