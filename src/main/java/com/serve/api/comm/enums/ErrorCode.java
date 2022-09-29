package com.serve.api.comm.enums;

public enum ErrorCode {
    /**
     * 错误码
     * 用户类的用7开通，金融类的用8开头，消息通知类的以9开头，
     */
    OK(200, "ok", "成功"),
    INVALID_TOKEN(403, "E_INVALID_TOKEN", "登录无效或者已过期"),

    E_UNDER_MAINTENCE(201, "E_UNDER_MAINTENCE", "系统维护中，预计30分钟后开放。"),
    NO_AUTHORITY(401, "NO_AUTHORITY", "没有权限"),
    E_NOT_FOUND(404, "E_NOT_FOUND", "找不到错误"),

    E_SERVER(500, "E_SERVER", "系统错误"),
    E_DIRTY_DATA(501, "E_DIRTY_DATA", "数据异常"),
    NETWORK_ERROR(503, "NETWORK_ERROR", "网络错误"),
    E_PARAM(505, "E_PARAM", "参数错误"),
    E_MISSING(506, "E_MISSING", "参数缺失"),

    FORBID_ACTION(510, "FORBID_ACTION", "禁止操作"),
    CONFIG_NOT_READY(512, "CONFIG_NOT_READY", "配置错误"),
    CONFIG_DISABLE(513, "CONFIG_DISABLE", "已被禁用"),
    ADMIN_AUTHOR_INVALID(514, "ADMIN_AUTHOR_INVALID", "运营后台运营码无效"),

    /**
     * 用户类错误代码
     */
    USER_HAD_EXISTS(601, "USER_HAD_EXISTS", "用户已存在"),
    USER_NOT_EXIST(602, "USER_NOT_EXIST", "用户不存在"),
    USER_UNNORMAL(603, "USER_UNNORMAL", "账号异常"),
    NEED_BIND_PHONE(604, "NEED_BIND_PHONE", "需要绑定手机号"),
    USER_WECHAT_LOGIN_FAIL(605, "USER_WECHAT_LOGIN_FAIL", "微信授权登录失败"),
    LOGIN_MOBILE_VERIFY_FAIL(606, "LOGIN_MOBILE_VERIFY_FAIL", "一键登录验证失败"),
    PHONE_NOT_EXIST(607, "PHONE_NOT_EXIST", "手机号未注册"),
    PHONE_HAD_EXIST(608, "PHONE_HAD_EXIST", "手机号已注册,请直接登录。"),
    CANNOT_FOLLOW_SELF(609, "CANNOT_FOLLOW_SELF", "不能关注自己"),
    WRONG_PASSWORD(610, "WRONG_PASSWORD", "密码错误"),
    NOT_IN_WHITE_NAME_LIST(611, "NOT_IN_WHITE_NAME_LIST", "不在白名单之列"),
    AUTHOR_LIMIT(612, "AUTHOR_LIMIT", "你已被禁止此操作"),
    PROVIDE_CERTIFICATION_FIRST(613, "PROVIDE_CERTIFICATION_FIRST", "请先提交实名认证信息"),
    USER_UNNORMAL_OTHER_SIDE(614, "USER_UNNORMAL_OTHER_SIDE", "对方账号异常"),

    NAME_PW_OR_ERROR(615, "NAME_PW_OR_ERROR", "帐号或者密码错误"),
    MUNBER_NEED(616, "MUNBER_NEED", "需要开通会员"),
    MUNBER_USER_NO_NEED_PAY(617, "MUNBER_USER_NO_NEED_PAY", "会员用户无需额外支付"),
    NO_LOCATION(618, "NO_LOCATION", "您的位置信息未知"),
    SOCIAL_BEYOND_MAX_TAG(619, "SOCIAL_BEYOND_MAX_TAG", "超过最大多选数目"),
    NOT_ONLINE(620, "NOT_ONLINE", "用户不在线"),
    BUSY(621, "BUSY", "用户忙"),
    BLACKLIST(622, "BLACKLIST", "已被加入黑名单"),
    INVALID_INVITE_CODE(623, "INVALID_INVITE_CODE", "无效邀请码"),
    USER_NOT_EXIST_PHONE(624, "USER_NOT_EXIST_PHONE", "该手机号不是平台用户，请先下载app注册."),
    HEAD_URL_PORN(625, "HEAD_URL_PORN", "头像违规，请重新上传"),
    HEAD_URL_TERROR(625, "HEAD_URL_TERROR", "头像违规，请重新上传"),
    HEAD_URL_AD(625, "HEAD_URL_AD", "头像违规，请重新上传"),
    SAYSOMETHING_ABNORMAL(626, "SAYSOMETHING_ABNORMAL", "签名违规，请更改"),
    NICKYNAME_ABNORMAL(627, "NICKYNAME_ABNORMAL", "昵称不合规范"),
    WECHAT_NO_SCAN(628, "WECHAT_NO_SCAN", "请先用微信扫码"),
    /**
     * 公共错误代码
     */
    HAD_PAIED(701, "HAD_PAIED", "已经支付过，永久免费"),
    NEED_PAIED(702, "NEED_PAIED", "需要购买会员或者付费"),
    INVALID_ORDER_STATUS(703, "INVALID_ORDER_STATUS", "订单状态无效"),
    ORDER_NOT_EXIST(704, "ORDER_NOT_EXIST", "订单不存在"),
    REMOTE_HTTP_SERVICE_ERROR(705, "REMOTE_HTTP_SERVICE_ERROR", "远程服务出错"),
    OSS_FILETYPE_WRONG(706, "OSS_FILETYPE_WRONG", "错误文件类型"),
    SMS_FAIL(707, "SMS_FAIL", "短信发送失败"),
    SMS_CODE_WRONG(708, "SMS_CODE_WRONG", "短信验证码过期或者无效"),
    CANNOT_TO_YOURSELF(709, "CANNOT_TO_YOURSELF", "不能对自己的进行操作"),
    PRIVACY_PERMISSI_NO(710, "PRIVACY_PERMISSI_NO", "隐私禁止"),
    CUSTOM_ERROR_MSG(711, "CUSTOM_ERROR_MSG", "自定义错误消息"),
    ONE_HUNDRED_LIMIT(712, "50 limit per times", "单次不得超过50"),
    CHANNEL_INVALID(712, "invalid channel", "app通过非授权渠道下载或者此渠道授权已失效，请前往其它渠道重新下载安装。"),
    HAD_EXIST(713, "had exist", "已经存在"),


    /**
     * 支付模块
     */
    PAY_INSUFFICIENT_BALANCE(802, "PAY_INSUFFICIENT_BALANCE", "余额不足"),
    INSUFFICIENT_BALANCE_CONSUME(803, "INSUFFICIENT_BALANCE_CONSUME", "充值即可使用"),
    PAY_WRONG_AMOUNT(804, "PAY_WRONG_AMOUNT", "金额错误"),
    PAY_WRONG_STATUS(805, "PAY_WRONG_STATUS", "当前状态无法支付"),
    PAYPAL_CONFIG_INVALID(806, "PAYPAL_CONFIG_INVALID", "paypal配置错误"),
    WALLET_NOT_EXIST(807, "WALLET_NOT_EXIST", "钱包账户不存在"),
    WALLET_UNNORMAL(808, "WALLET_UNNORMAL", "钱包异常"),
    PAY_FAILED(809, "PAY_FAILED", "支付失败"),
    PAY_NOT_SUPPORT_METHOD(810, "PAY_NOT_SUPPORT_METHOD", "不支付的支付方式"),
    PAY_NO_WITHDRAW_METHOD(811, "PAY_NO_WITHDRAW_METHOD", "无提现方式,请先关注公众号，并关联账号，提现金额将会进入微信零钱"),
    PAY_NO_CERTIFICATION(812, "PAY_NO_CERTIFICATION", "未实名认证"),
    PAY_INVALID_APPLEPAY_OPTION(813, "PAY_INVALID_APPLEPAY_OPTION", "无效的苹果支付配置"),
    PAY_WRONG_APPLEPAY_PRODUCTID(814, "PAY_WRONG_APPLEPAY_PRODUCTID", "pruductId不一致"),
    PAY_INVALID_APPLEPAY_BUNDLEID(815, "PAY_INVALID_APPLEPAY_BUNDLEID", "无效的苹果支付"),
    PAY_WITHDRAW_BALANCEM_LESS_THEN_MIN(816, "PAY_WITHDRAW_BALANCEM_LESS_THEN_MIN", "账户余额大于平台要求值才可提现"),
    CANNOT_UPDATE_AFTER_WITHDRAW_SUCCESS(817, "CANNOT_UPDATE_AFTER_WITHDRAW_SUCCESS", "已提现成功不能再更改"),
    PAY_NO_WITHRAW_METHOD(818, "PAY_NO_WITHRAW_METHOD", "请先关注【贵圈文化】公众号，并关联账号，提现金额将会进入您的微信零钱"),
    WITHDRAW_EXCEED_MAX(819, "WITHDRAW_EXCEED_MAX", "超出今日可提金额"),


    /**
     * 内容消费模块
     */
    EMPTY_CONTENT(901, "EMPTY_CONTENT", "内容不能为空"),
    OUT_OF_MAX_IMAGE(902, "OUT_OF_MAX_IMAGE", "超过最大张数限制"),
    POST_FREE(903, "POST_FREE", "无需支付"),
    HAS_LIKES(904, "HAS_LIKES", "已点过赞"),
    VIDEO_OUT_OF_TIME_LENGTH_RANGE(905, "VIDEO_OUT_OF_TIME_LENGTH_RANGE", "请控制在规定时长内"),
    NEED_HIGHT_FORTUNE_LEVEL(906, "HAS_LIKES", "精彩动态，需要更高的财富等级才可查看"),
    POST_NOT_EXIST(907, "the post is not exist or had been deleted or shield", "动态不存在或者已下线"),


    /**
     * 消息和推送
     */
    CHAT_FORBID(1001, "CHAT_FORBID", "你已被老师禁言"),
    PUSH_CONFIG_FAILED(1002, "PUSH_CONFIG_FAILED", "推送配置失败"),
    MESSAGE_NOT_EXIST(1003, "MESSAGE_NOT_EXIST", "消息不存在"),
    MESSAGE_NO_NEED_PAY(1004, "MESSAGE_NO_NEED_PAY", "消息不需要付费查看"),
    OUT_OF_MAX_FREE_QUANTITY(1005, "OUT_OF_MAX_FREE_QUANTITY", "超出每日最大免费数"),
    MESSAGE_SERVER_UNREACHABLE(1006, "MESSAGE_SERVER_UNREACHABLE", "消息服务器不可用"),
    VIDEOCHAT_WRONG_STATUS(103, "VIDEOCHAT_WRONG_STATUS", "当前状态不允许此操作"),
    MESSAGE_UNKOWN_ERROR(1003, "MESSAGE_UNKOWN_ERROR", "消息未知错误"),

    /**
     * 直播
     */
    LIVE_END(1101, "LIVE_END", "直播已结束"),
    UPDATE_LAYOUT_ERROR(1102, "UPDATE_LAYOUT_ERROR", "更新布局出错"),
    RECORDING_BUSY(1103, "RECORDING_BUSY", "录制系统繁忙，请稍后再试"),

    ;


    private int code;
    private String value;
    private String valueCn;

    ErrorCode(int code, String value, String valueCn) {
        this.code = code;
        this.value = value;
        this.valueCn = valueCn;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
