package com.serve.api.auth;

import com.serve.api.auth.entity.Menu;
import com.serve.api.auth.entity.Role;
import com.serve.api.auth.service.AuthService;
import com.serve.api.comm.model.Result;
import com.serve.api.user.entity.User;
import com.serve.api.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/")
@Slf4j
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;
    private UserService userService;

    @ApiOperation(value = "菜单列表", notes = "")
    @GetMapping("getMenuList")
    public Result<Menu> getMenuList() {
        List<Menu> menus = authService.getMenuList();
        return Result.okResult(menus);
    }

    @ApiOperation(value = "角色列表", notes = "")
    @GetMapping("getRoleList")
    public Result<Role> getRoleList() {
        List<Role> roles = authService.getRoleList();
        return Result.okResult(roles);
    }

    @ApiOperation(value = "角色列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "name", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "phone", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNo", value = "", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "", required = false, dataType = "Integer"),
    })
    @GetMapping("getUserList")
    public Result<Page<User>> getUserList(String name, String phone, String email, Integer roleId, Integer pageNo, Integer pageSize) {
        Page<User> users = authService.findUserBy(name, phone, email, roleId, pageNo, pageSize);
        return Result.okResult(users);
    }

}
