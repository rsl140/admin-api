package com.serve.api.comm.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MemoryUtil {


    private static Timer timer = null;
    private static ConcurrentHashMap concurrentHashMap = null;
    private static ConcurrentHashMap<Object, Integer> expireTimeConMap = null;
    private static MyTimerTask myTimerTask = null;
    private static Long Frquency = 60 * 60 * 1000L;
    private static Integer defaultExpiredTIme = 24 * 60 * 60 * 1000;
    /**
     * 永不过期，定为10年
     */
//    private static Long NEVER = 10*365*defaultExpiredTIme;

    public static Integer ONE_MIN = 60 * 1000;
    public static Integer FIVE_MIN = 60 * 1000 * 5;
    public static Integer TEN_MIN = 60 * 1000 * 10;
    public static Integer FIFTEEN_MIN = 60 * 1000 * 15;
    public static Integer TWENTY_MIN = 60 * 1000 * 20;

    public static Integer ONE_HOUR = 60 * 1000 * 60;
    public static Integer ONE_DAY = 24 * 60 * 1000 * 60;
    public static Integer FIVE_HOUR = 5 * 60 * 1000 * 60;

    static {
        timer = new Timer();
        myTimerTask = new MyTimerTask();
        concurrentHashMap = new ConcurrentHashMap();
        expireTimeConMap = new ConcurrentHashMap<Object, Integer>();
        timer.scheduleAtFixedRate(myTimerTask, Frquency, Frquency);
    }

    /**
     * 默认24小时过期
     *
     * @param key
     * @param value
     */
    public static boolean put(Object key, Object value) {
        return put(key, value, (int) (System.currentTimeMillis() + defaultExpiredTIme));
    }

    /**
     * 存放不过期的缓存:除非没有其它办法，如次没有持久化的数据，否则为了保证最终一致性，缓存的过期时间最好不超过1天
     *
     * @param key
     * @param value
     */
    public static boolean putNeverExpired(Object key, Object value) {
        return put(key, value);
    }

    /**
     * @param key
     * @param value
     * @param expireTime 过期时间，单位毫秒
     */
    public static boolean put(Object key, Object value, Integer expireTime) {
        if (key == null || value == null) {
            return false;
        }
        Object obj0 = concurrentHashMap.put(key, value);
        Object obj1 = expireTimeConMap.put(key, (int) (System.currentTimeMillis() + expireTime));
        if (obj0 == null || obj1 == null) {
            return false;
        }
        return true;
    }


    public static <T> T get(Object key) {
        Integer expiretime = expireTimeConMap.get(key);
        Integer currentDatetimeLong = (int) System.currentTimeMillis();
        if (expiretime == null) {
            return null;
        }
        if (expiretime < currentDatetimeLong) {
            expireTimeConMap.remove(key);
            concurrentHashMap.remove(key);
            return null;
        }
        Object obj = concurrentHashMap.get(key);
        if (obj == null) {
            return null;
        }
        return (T) obj;
    }

    static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            try {

                int count = 0;
                Iterator iterator = expireTimeConMap.keySet().iterator();
                Integer currentDatetimeLong = (int) System.currentTimeMillis();
//                if(iterator.hasNext()){
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    Integer expireDatetime = expireTimeConMap.get(key);
                    if (expireDatetime.longValue() < currentDatetimeLong) {
                        expireTimeConMap.remove(key);
                        concurrentHashMap.remove(key);
                        count++;
                    }
                }
                log.info("定时清除内存缓存个数：{}", count);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("定时清除过期内存出错：{}", e);
            }
        }
    }

    public static boolean remove(Object key) {
        if (key == null) {
            return false;
        }
        Object obj0 = concurrentHashMap.remove(key);
        Object obj1 = expireTimeConMap.remove(key);
        if (obj0 != null && obj1 != null) {
            return true;
        }
        return false;
    }

    public static void clearAll() {
        concurrentHashMap.clear();
        expireTimeConMap.clear();
    }


    /**
     * 更新原有值，过期时间不变
     *
     * @param key
     * @param value
     * @return
     */
    public static Boolean putUpdate(Object key, Object value) {
        if (key == null || value == null) {
            return false;
        }
        Object value2 = concurrentHashMap.get(key);
        if (value2 == null) {
            return false;
        }
        concurrentHashMap.put(key, value);
        return true;
    }

    public static boolean iscontainsKey(Object key) {
        if (key == null) {
            return false;
        }
        return concurrentHashMap.containsKey(key);
    }


}
