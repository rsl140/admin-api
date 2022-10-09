package com.serve.api.user;

import com.serve.api.comm.config.JwtHelper;
import com.serve.api.comm.model.Result;
import com.serve.api.user.entity.User;
import com.serve.api.user.enums.LoginType;
import com.serve.api.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "loginType", value = "登录类型：PASSWORD-密码登录；SMS-短信验证码登录,MOBILE_ONE_PASS-一键登录，WECHAT-微信登录,，WEIBO-微博登录，QQ-QQ登录", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "param", value = "密码登陆是，传密码；短信-短信验证码；一键登录-运营商token；第三方登陆时-第三方code；当WECHAT_GZH_WEBAPP时，param传ticket", required = false, dataType = "String"),
    })
    @PostMapping("vt/login")
    public synchronized Result login(LoginType loginType, String email, String param) {
        Map<String, Object> map = userService.login(loginType, email, param);
        return Result.okResult(map);
    }


//    @ApiOperation(value = "登录", notes = "")
//    @ApiImplicitParams({
//    })
//    @PostMapping("vt/login")
//    public Result login(@RequestBody User user) {
//        return Result.okResult(user);
//    }


    @ApiOperation(value = "邮箱+密码注册接口", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
    })
    @PostMapping(value = {"vt/register"})
    public synchronized Result register(String email, String password) {
        User user = userService.registerByEmail(email, password);
        return Result.okResult(JwtHelper.createJWT(String.valueOf(user.getId())));
    }

    //    用户信息
    @ApiOperation(value = "用户信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "Authorization", value = "token", required = false, dataType = "String"),

    })
    @GetMapping("userInfo")
    public Result getUserInfo() {
        return Result.okResult(null);
    }
}
