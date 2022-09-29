package com.serve.api.user.enums;

import io.swagger.annotations.ApiModel;

@ApiModel("组织类别")
public enum OrganizationType {
    MANAGE("管理"),
    CLIENT("客户");

    private String value;

    OrganizationType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
