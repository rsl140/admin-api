package com.serve.api.comm.service;


import com.serve.api.comm.enums.CacheType;

public class CacheKey {

    public static String getBrokerListKey() {
        return "BROKER_MAP_KEY";
    }

    public static String getBrokerKey(String brokerUrl) {
        return "BK" + brokerUrl;
    }

    public static String getBrokerMqttClientKey(String brokerUrl) {
        return "BROKER_MQTTCLIENT_" + brokerUrl;
    }

    public static Object getUserOnlineStatusKey(String clientId) {
        return "ON" + clientId;
    }

    public static String getMsgFreeUsedQuantityKey(int userId) {
        return "OMsgFreeUsedQuantity_" + userId;
    }

    public static String getUserChatInfoKey(String clientId) {
        return "USER_CHAT_INFO" + clientId;
    }

    public static Object getSmsCodeKey(String phone) {
        return "SMS_CODE_" + phone;
    }

    public static String getAppmarketReviewUserIdSetKey() {
        return "APPMARKET_REVIEW_USERID_SET";
    }


    public static Object getCacheKey(CacheType cacheType, Object key) {
        switch (cacheType) {
            case APPMARKET_REVIEW_USERID_SET:
                return getAppmarketReviewUserIdSetKey();
            case USER_CHAT_INFO:
                return getUserChatInfoKey((String) key);
            case MAX_FREE_MSG_PER_DAY:
                return getMaxFreeMsgPerdayKey();
            default:
                break;
        }
        return null;
    }

    public static String getChatbotUserIdListKey() {
        return "ChatbotUserIdListKey";
    }


    public static Object getChatscriptPackageKey() {
        return "ChatscriptPackageKey";
    }

    public static Object getYestodayDateKey() {
        return "YestodayDateKey";
    }

    public static Object getAllUserChatbotInfoListKey() {
        return "AllUserChatbotInfoListKey";
    }

    public static Object getPushQuantityInOneMinuteKey() {
        return "PushQuantityInOneMinuteKey";
    }

    public static Object getMaxFreeMsgPerdayKey() {
        return "MaxFreeMsgPerdayKey";
    }

    public static Object getApplepayTransactionIdKey(String transactionId) {
        return "ApplepayTransactionIdKey" + transactionId;
    }

    public static Object getClientVideochatStatusKey(int videochatId) {
        return "ClientVideochatStatusKey" + videochatId;
    }

    public static String getVideochatMessageKey(int videochatId, int userId) {
        return "VideochatMessageKey_" + videochatId + "_" + userId;
    }

    public static String getChannelListKey() {
        return "ChannelListKey";
    }
}
