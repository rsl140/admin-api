package com.serve.api.comm.service.impl;

import com.serve.api.auth.entity.Menu;
import com.serve.api.auth.entity.Permission;
import com.serve.api.auth.entity.Role;
import com.serve.api.auth.entity.RolePermission;
import com.serve.api.auth.repository.*;
import com.serve.api.comm.enums.EnableStatus;
import com.serve.api.comm.service.CommService;
import com.serve.api.user.entity.User;
import com.serve.api.user.entity.UserRole;
import com.serve.api.user.repository.UserRepository;
import com.serve.api.user.repository.UserRoleRepository;
import com.serve.api.user.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础数据初始化
 */
@Service
public class CommServiceImpl implements CommService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSecurityService userSecurityService;


    private void creatOriginRole() {
        Role role = roleRepository.findById(1).orElse(new Role());
        role.setName("SUPER");
        role.setDescription(("超级管理员"));
        roleRepository.save(role);

        role = roleRepository.findById(2).orElse(new Role());
        role.setName("MANAGER");
        role.setDescription(("管理员"));
        roleRepository.save(role);

        role = roleRepository.findById(3).orElse(new Role());
        role.setName("MEMBER");
        role.setDescription(("会员"));
        roleRepository.save(role);


    }

    private void creatOriginPermission() {
        Permission permission = permissionRepository.findById(1).orElse(new Permission());
        permission.setName("SUPER");
        permission.setDescription(("超级管理员"));
        permissionRepository.save(permission);

        permission = permissionRepository.findById(2).orElse(new Permission());
        permission.setName("ORG_ROOT");
        permission.setDescription(("仅次于super"));
        permissionRepository.save(permission);

        permission = permissionRepository.findById(3).orElse(new Permission());
        permission.setName("DATA_ENTRY");
        permission.setDescription(("仅能提交数据"));
        permissionRepository.save(permission);
    }

    private void creatOriginRolePermission() {
        RolePermission rolePermission = rolePermissionRepository.findById(1).orElse(new RolePermission());
        rolePermission.setRoleId(1);
        rolePermission.setPermissionId(1);
        rolePermissionRepository.save(rolePermission);

        rolePermission = rolePermissionRepository.findById(2).orElse(new RolePermission());
        rolePermission.setRoleId(2);
        rolePermission.setPermissionId(2);
        rolePermissionRepository.save(rolePermission);

        rolePermission = rolePermissionRepository.findById(3).orElse(new RolePermission());
        rolePermission.setRoleId(3);
        rolePermission.setPermissionId(3);
        rolePermissionRepository.save(rolePermission);
    }

    private void creatOriginMenu() {
        Menu menu = menuRepository.findById(1).orElse(new Menu());

        menu.setPId(0);
        menu.setPPath("0,1");
        menu.setName("Auth");
        menu.setTitle("权限管理");
        menu.setPath("/auth");
        menu.setComponent("layout");
        menu.setIcon("el-icon-s-help");
        menu.setSort("99");
        menu.setStatus(EnableStatus.ENABLE);
        menuRepository.save(menu);
    }

    private void creatSupperAdminAccount() {
        User user = userRepository.findFirstByName("admin");
        if (user == null) {
            User admin = new User();
            admin.setName("admin");
            user = userRepository.save(admin);
            userSecurityService.setUserPassword(user.getId(), "8cd9b7b71a38cd63fee4063ec10bb170");
        } else {
            user.setName("admin");
            user.setStatus(EnableStatus.ENABLE);
            user = userRepository.save(user);
            userSecurityService.setUserPassword(user.getId(), "8cd9b7b71a38cd63fee4063ec10bb170");
        }
        creatOriginUserRole(user.getId());

    }

    private void creatOriginUserRole(int adminId) {
        UserRole userRole = userRoleRepository.findById(1).orElse(new UserRole());
        userRole.setRoleId(1);
        userRole.setUserId(adminId);
        userRoleRepository.save(userRole);
    }


    @Override

    public void initOririnData() {
        creatOriginRole();
        creatOriginPermission();
        creatOriginRolePermission();
        creatSupperAdminAccount();
        creatOriginMenu();
    }
}
