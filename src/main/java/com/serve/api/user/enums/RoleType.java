package com.serve.api.user.enums;

import io.swagger.annotations.ApiModel;

@ApiModel("角色")
public enum RoleType {
    SUPER("超级管理员"),
    MEMBER("管理员"),
    MANAGER("会员");

    private String value;

    RoleType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
