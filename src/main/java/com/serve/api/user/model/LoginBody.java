package com.serve.api.user.model;

import com.serve.api.user.enums.LoginType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginBody {
    @ApiModelProperty(value = "账号：可以是邮箱/手机号/用户名")
    private String account;
    @ApiModelProperty(value = "登录类型：PASSWORD-密码登录；SMS-短信验证码登录,MOBILE_ONE_PASS-一键登录，WECHAT-微信登录,，WEIBO-微博登录，QQ-QQ登录")
    private LoginType loginType;
    @ApiModelProperty(value = "密码登陆是，传密码；短信-短信验证码；一键登录-运营商token；第三方登陆时-第三方code；当WECHAT_GZH_WEBAPP时，param传ticket")
    private String param;
}
