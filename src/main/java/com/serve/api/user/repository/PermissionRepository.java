package com.serve.api.user.repository;

import com.serve.api.user.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query(nativeQuery = true, value = "SELECT p.* FROM permission p,role_permission rp, role r WHERE r.id IN ?1 AND rp.role_id=r.id AND rp.permission_id=p.id;")
    List<Permission> findPermissionByRoleId(List<Integer> roleIds);
}
