package com.serve.api.user.enums;

import io.swagger.annotations.ApiModel;

@ApiModel("生理性别")
public enum Gender {
    MALE("男"),
    FEMALE("女"),
    ;

    private String desc;

    Gender(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
