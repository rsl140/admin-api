package com.serve.api.comm.enums;

public enum ConfigType {
    APPLE_REVIEW_SWITCH("苹果应用市场审核开关，IOS过审配置，ON开启，OFF关闭"),
    ;

    private String valueCn;

    ConfigType(String valueCn) {
        this.valueCn = valueCn;
    }

    public String getValueCn() {
        return valueCn;
    }

    public void setValueCn(String valueCn) {
        this.valueCn = valueCn;
    }
}
