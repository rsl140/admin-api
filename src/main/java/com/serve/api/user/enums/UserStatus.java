package com.serve.api.user.enums;

public enum UserStatus {
    NORMAL(1, "NORMAL", "正常"),
    SHIELD(2, "SHIELD", "禁闭"),
    BANNED(3, "BANNED", "封号"),
    DELETE(4, "DELETE", "删除"),
    ;

    private int type;
    private String value;
    private String valueCn;

    UserStatus(int type, String value, String valueCn){
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
