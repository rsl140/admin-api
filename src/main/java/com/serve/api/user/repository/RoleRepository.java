package com.serve.api.user.repository;

import com.serve.api.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(nativeQuery = true, value = "SELECT r.* FROM `user` u,role r,user_role ur WHERE u.id=?1 AND u.id=ur.user_id AND ur.role_id = r.id;")
    List<Role> findRoleByUserId(Integer userId);
}
