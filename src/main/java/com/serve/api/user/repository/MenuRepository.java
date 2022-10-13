package com.serve.api.user.repository;

import com.serve.api.user.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(nativeQuery = true, value = "SELECT m.* FROM `menu` m, role_menu rm, role r WHERE r.id IN(?1) AND rm.role_id=r.id AND rm.menu_id=m.id;")
    List<Menu> findMenuByRoleId(List<Integer> roleIds);
}
