package com.serve.api.comm.enums;

public enum CacheType {
    SLID_UNLOCK_CODE(1, "滑动解锁校验码"),


    CONFIG(1, "适用于config表种所有的配置"),
    LEVEL_RULE(1, "level_rule表种所有的配置"),
    FORTUNE_LEVEL_RULE(1, "fortune_level_rulbe表种所有的配置"),
    POST_LEVEL_RULE(1, "post_level_rulbe表种所有的配置"),
    CHANNEL(1, "CHANNEL表种所有的配置"),
    FILTER_USER(1, "filter_user表缓存,首页推荐屏蔽用户"),


    //以下的未来会被取代
    APPMARKET_REVIEW_USERID_SET(1, "应用市场审核用户id"),
    USER_CHAT_INFO(1, "用户的聊天信息"),
    MAX_FREE_MSG_PER_DAY(1, "每日最多免费私信数"),


    ;

    private int type;
    private String valueCn;

    CacheType(int type, /*String value,*/ String valueCn) {
        this.type = type;
        this.valueCn = valueCn;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getValueCn() {
        return valueCn;
    }

    public void setValueCn(String valueCn) {
        this.valueCn = valueCn;
    }
}
