package com.serve.api.auth.repository;

import com.serve.api.auth.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer> {
}
