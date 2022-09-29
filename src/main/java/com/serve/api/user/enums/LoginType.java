package com.serve.api.user.enums;

public enum LoginType {
    PASSWORD(1, "PASSWORD", "密码登录"),
    SMS(2, "SMS", "短信验证码登录"),
    MOBILE_ONE_PASS(2, "MOBILE_ONE_PASS", "一键登录"),
    WECHAT(2, "WECHAT", "微信移动APP登录"),
    WECHAT_GZH(2, "WECHAT_GZH", "微信公众号登录，在微信浏览器中打开"),
    WECHAT_GZH_WEBAPP(2, "WECHAT_GZH_WEBAPP", "网页应用扫描登陆（在非微信内嵌浏览器打开），微信公众号授权"),
    WECHAT_WEBAPP(2, "WECHAT_WEBAPP", "微信网站应用登录"),
    WEIBO(2, "WEIBO", "微博登录"),
    QQ(2, "QQ", "QQ登录"),
    APPLE(2, "APPLE", "苹果登录"),
    ;

    private int type;
    private String value;
    private String valueCn;

    LoginType(int type, String value, String valueCn) {
        this.type = type;
        this.value = value;
        this.valueCn = valueCn;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueCn() {
        return valueCn;
    }

    public void setValueCn(String valueCn) {
        this.valueCn = valueCn;
    }
}
