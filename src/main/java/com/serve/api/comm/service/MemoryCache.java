package com.serve.api.comm.service;

import com.serve.api.comm.enums.CacheType;
import com.serve.api.comm.utils.DateUtil;
import com.serve.api.comm.utils.MemoryUtil;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MemoryCache {

    public static void addOnlineUser(String clientId, String serverURI) {
        MemoryUtil.putNeverExpired(CacheKey.getUserOnlineStatusKey(clientId), serverURI);
    }

    public static void removeOnlineUser(String clientId) {
        MemoryUtil.remove(CacheKey.getUserOnlineStatusKey(clientId));
    }

    public static boolean getUserOnlineStatus(String clientId) {
        String brokerUrl = MemoryUtil.get(CacheKey.getUserOnlineStatusKey(clientId));
        if (StringUtils.isEmpty(brokerUrl)) {
            return false;
        }
        return true;
    }

    public static String getUserOnline(String clientId) {
        String brokerUrl = MemoryUtil.get(CacheKey.getUserOnlineStatusKey(clientId));
//        if (StringUtils.isEmpty(brokerUrl)) {
//            return null;
//        }
        return brokerUrl;
    }

    public static void removeUserChatInfo(String clientId) {
        MemoryUtil.remove(CacheKey.getUserChatInfoKey(clientId));
    }

    /**
     * 缓存短信验证码
     *
     * @param phone
     * @param smsCode
     */
    public static void putSmsCode(String phone, String smsCode) {
        MemoryUtil.put(CacheKey.getSmsCodeKey(phone), smsCode, MemoryUtil.FIVE_MIN);
    }

    public static String getSmsCode(String phone) {
        return MemoryUtil.get(CacheKey.getSmsCodeKey(phone));
    }

    public static void removeSmsCode(String phone) {
        MemoryUtil.remove(CacheKey.getSmsCodeKey(phone));
    }


    public static void removeCache(CacheType cacheType, Object key) {
        MemoryUtil.remove(CacheKey.getCacheKey(cacheType, key));
    }

    public static void removeCache(Object key) {
        MemoryUtil.remove(key);
    }

    public static <T> T getCache(CacheType cacheType, Object key) {
        return MemoryUtil.get(CacheKey.getCacheKey(cacheType, key));
    }

    public static <T> void putCache(CacheType cacheType, Object key, T data) {
        MemoryUtil.put(CacheKey.getCacheKey(cacheType, key), data, MemoryUtil.ONE_DAY);
    }

    public static Date getYestodayDate() {
        Date date = MemoryUtil.get(CacheKey.getYestodayDateKey());
        if (date == null) {
            date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
            MemoryUtil.put(CacheKey.getYestodayDateKey(), date, (int) (DateUtil.getDayEndTime().getTime() - System.currentTimeMillis()));
        }
        return date;
    }


    public static Integer getPushQuantityInOneMinuteKey() {
        return MemoryUtil.get(CacheKey.getPushQuantityInOneMinuteKey());
    }

    public static void putPushQuantityInOneMinute(Integer quantity) {
        MemoryUtil.put(CacheKey.getPushQuantityInOneMinuteKey(), quantity, MemoryUtil.ONE_MIN);
    }

    public static void putUpdatePushQuantityInOneMinute2(Integer quantity) {
        MemoryUtil.putUpdate(CacheKey.getPushQuantityInOneMinuteKey(), quantity);
    }

    public static String getApplepayTransactionId(String applepayTransactionId) {
        return MemoryUtil.get(CacheKey.getApplepayTransactionIdKey(applepayTransactionId));
    }

    public static void putApplepayTransactionId(String transactionId) {
        MemoryUtil.put(CacheKey.getApplepayTransactionIdKey(transactionId), transactionId, 10 * 1000);
    }


    public static Map<String, Integer> getBlacklist2() {
        String key = "Blacklist";
        return MemoryUtil.get(key);
    }

    public static void putBlacklist(Map<String, Integer> blacklistListMap) {
        String key = "Blacklist";
        if (blacklistListMap == null) {
            MemoryUtil.remove(key);
        } else {
            MemoryUtil.put(key, blacklistListMap);
        }
    }

    public static List<String> getNickName(String part) {
        String key = "NickName" + part;
        return MemoryUtil.get(key);
    }

    public static void putNickName(String part, List<String> list) {
        String key = "NickName" + part;
        if (list == null) {
            MemoryUtil.remove(key);
        } else {
            MemoryUtil.putNeverExpired(key, list);
        }
    }


    /**
     * 获取小颗粒度的同步锁，只能是内存缓存
     *
     * @param lockKey
     * @return
     */
    public static synchronized String getLock(String lockKey) {
        String lock = MemoryUtil.get(lockKey);
        if (StringUtils.isEmpty(lock)) {
            MemoryUtil.put(lockKey, lockKey, MemoryUtil.FIFTEEN_MIN);
            lock = MemoryUtil.get(lockKey);
        }
        return lock;
    }

    public static final String WECHAT_CODE_BASE_KEY = "WECHAT_CODE_";

    private static String getWechatCodeKey(String wechatCode) {
        return WECHAT_CODE_BASE_KEY + wechatCode;
    }

    public static boolean putWechatInfo(String wechatCode, String[] openIdAndAcToken) {
        return MemoryUtil.put(getWechatCodeKey(wechatCode), openIdAndAcToken, MemoryUtil.ONE_HOUR);
    }

    public static String[] getWechatInfo(String wechatCode) {
        return MemoryUtil.get(getWechatCodeKey(wechatCode));
    }


}