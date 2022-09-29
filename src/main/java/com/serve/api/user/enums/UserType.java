package com.serve.api.user.enums;

/**
 *
 */
public enum UserType {
    USER("用户"),
    ADMIN("管理员"),
    SYSTEM("系统"),
    ;

    private String value;

    UserType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
