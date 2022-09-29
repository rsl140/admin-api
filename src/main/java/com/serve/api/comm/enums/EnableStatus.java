package com.serve.api.comm.enums;

public enum EnableStatus {
    ENABLE("有效"),
    DISABLE("禁用"),
    DELETED("删除"),
    ;

    private String value;

    EnableStatus(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
