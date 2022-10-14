package com.serve.api.auth.enums;

import io.swagger.annotations.ApiModel;

@ApiModel("权限")
public enum PermissionType {
    SUPER("所有权限"),
    ORG_ROOT("仅次于super"),
    DATA_ENTRY("仅能提交数据");

    private String value;

    PermissionType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
