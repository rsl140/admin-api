package com.serve.api.comm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serve.api.comm.config.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CommUtils {
    private static String SPLIT_KEY = ",";

    public static String createRandomNo() {
        //格式化当前时间
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDate = sfDate.format(new Date());
        String random = getRandom620(1);
        //最后得到20位订单编号。
        return strDate + random;
    }

    /**
     * 获取6-10 的随机位数数字
     *
     * @param length 想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
            result += randInt;
        }
        return result;
    }


    /**
     * 将以逗号隔开的用户ID字符串，转换成List<Integer>
     **/
    public static List<Integer> getLongFromString(String string) {
        List<Integer> userIdList = new ArrayList<>();
        if (StringUtils.isEmpty(string)) {
            return userIdList;
        }
        String[] userIdsArr = string.split(SPLIT_KEY);
        for (String idStr : userIdsArr) {
            if (!StringUtils.isEmpty(idStr) && !"null".equals(idStr) && !"undentify".equals(idStr)) {
                try {
                    userIdList.add(Integer.parseInt(idStr));
                } catch (NumberFormatException e) {
                    log.error("error:", e);
                }
            }
        }
        return userIdList;
    }

    /**
     * 将以逗号隔开的用户ID字符串，转换成List<Integer>
     **/
    public static List<String> getStringListFromString(String string) {
        List<String> userIdList = new ArrayList<>();
        if (StringUtils.isEmpty(string)) {
            return userIdList;
        }
        String[] userIdsArr = string.split(SPLIT_KEY);
        for (String idStr : userIdsArr) {
            if (!StringUtils.isEmpty(idStr) && !"null".equals(idStr) && !"undentify".equals(idStr)) {
                userIdList.add(idStr);
            }
        }
        return userIdList;
    }


    /**
     * 将list1中同时也在list2中的元素移除
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> removeRepeat(List<Integer> list1, List<Integer> list2) {
        if (CollectionUtils.isEmpty(list1) || CollectionUtils.isEmpty(list2)) {
            return list1;
        }
        int len1 = list1.size();
        for (int i = len1 - 1; i >= 0; i--) {
            int len2 = list2.size();
            for (int j = len2 - 1; j >= 0; j--) {
                if (list1.get(i).longValue() == list2.get(j)) {
                    list1.remove(i);
                    break;
                }
            }
        }
        return list1;
    }


    private static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    public static boolean isContainChinese(String str) {
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 毫秒
     *
     * @param time
     */
    public static void sleep(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static String jackSontoJsonString(Object obj) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("jackson to json string error:", e);
        }

        return null;

    }

    public static <T> T jackSonFromJsonString(String jsonString, Class<T> objClass) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return mapper.readValue(jsonString, objClass);
        } catch (IOException e) {

            log.error("jackson from string error:", e);

        }
        return null;


    }

    public static <T> T jackSonFromJsonString(String jsonString, TypeReference typeReference) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return mapper.readValue(jsonString, typeReference);
        } catch (IOException e) {

            log.error("jackson from string error:", e);

        }
        return null;

    }


    public static boolean isVideo(String contentType) {
        if ("video".equals(contentType)) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * @param min eg:0.01
     * @param max eg:0.05
     *            return java.lang.String
     * @Author monik
     * @Description 获取区间内随机值
     * @Date 5:16 下午 2021/9/7
     */
    public static String randomBetween(String min, String max) {
        BigDecimal minDecimal = new BigDecimal(min);
        BigDecimal maxDecimal = new BigDecimal(max);
        int scale = Integer.max(minDecimal.scale(), maxDecimal.scale());
        BigDecimal times = BigDecimal.ONE;
        for (int i = 0; i < scale; i++) {
            times = times.multiply(BigDecimal.TEN);
        }
        int minVal = minDecimal.multiply(times).intValue();
        int maxVal = maxDecimal.multiply(times).intValue();
        Random random = new Random();
        BigDecimal rand = new BigDecimal(random.nextInt(maxVal) % (maxVal - minVal + 1) + minVal);
        return rand.divide(times).toString();
    }

    /***
     * @Author monik
     * @Description 比较两个版本的大小
     * @Date 10:17 上午 2021/9/8
     * @param v1
     * @param v2
     * return int 0代表相等，1代表左边(v1)大，-1代表右边(v2)大
     */
    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        }
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        Integer diff = 0;

        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    //根据老师ID生成手机摄像头推流UID   摄像头ID = 1亿+userid
    public static Long generatePhoneCameraUid(Integer teacherId) {
        return teacherId.longValue() + 100000000;
    }

    //根据老师ID生成屏幕共享推流UID  摄像头ID = 2亿+userid
    public static Long generateScreenShareUid(Integer teacherId) {
        return teacherId.longValue() + 200000000;
    }

    public static String leftFillingZero(String source, int len) {
        if (source.length() < len) {
            int fillingNum = len - source.length();
            StringBuilder sb = new StringBuilder(fillingNum);
            for (int i = 0; i < fillingNum; i++) {
                sb.insert(0, "0");
            }
            sb.append(source);
            return sb.toString();
        } else {
            return source;
        }
    }

    //字符串数组去重
    public static String[] uniqueStringArray(String[] s) {
        List list = Arrays.asList(s);
        Set set = new HashSet(list);
        String[] rid = (String[]) set.toArray(new String[0]);
        return rid;
    }

    //判断是否是临时教室，赋能课和直播课直播演练
    public static boolean isTempRoom(String roomId) {
        return roomId.startsWith("en_") || roomId.startsWith("elc_");
    }

    public static boolean isEnergizeRoom(String roomId) {
        return roomId.startsWith("en_");
    }

    public static boolean isExerciseRoomId(String roomId) {
        return roomId.startsWith("elc_");
    }

    public static String generateEnergizeRoomId(Integer coursecommId, Integer unitId, Integer userId) {
        //格式 en_课程包ID-单元ID
        return "ec_" + userId + "-" + CommUtils.leftFillingZero(String.valueOf(coursecommId), 5) + "-" + CommUtils.leftFillingZero(String.valueOf(unitId), 5);
    }

    public static String generateExerciseRoomId(String roomId) {
        //格式 en_课程包ID-单元ID
        return "e" + roomId;
    }

    public static String getRoomIdIfExercise(String roomId) {
        if (isExerciseRoomId(roomId)) {
            roomId = roomId.substring(1);
        }
        return roomId;
    }

    public static String getOrderNo() {
        //格式化当前时间
//        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = sfDate.format(new Date());
        String random = getRandom620(2);
        //最后得到20位订单编号。
        return strDate + random;
    }


    public static String generateRoomId(Integer lessonId) {
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = sfDate.format(new Date());
        return "lc" + "-" + CommUtils.leftFillingZero(String.valueOf(lessonId), 6) + strDate;
    }

    public static <T> List copyList(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                Class<?> cl = list.get(i).getClass();
                Object o = null;
                try {
                    o = cl.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                BeanUtils.copyProperties(list.get(i), o);
                list.set(i, (T) o);
            }
        }
        return list;
    }

    public static Pageable getPageable(Integer pageNo, Integer pageSize) {
        if (!CommUtils.isValidInt(pageNo)) {
            pageNo = 1;
        }
        if (!CommUtils.isValidInt(pageSize)) {
            pageSize = 20;
        }
        return PageRequest.of(pageNo - 1, pageSize);
    }

    public static boolean isValidInt(Integer id) {
        if (id != null && id != Constant.NONE_ID) {
            return true;
        } else {
            return false;
        }
    }

    public static String getSuffix(String fileName) {
        if (fileName.lastIndexOf(".") == -1) {
            return "";//文件没有后缀名的情况
        }
        //此时返回的是带有 . 的后缀名，
        return fileName.substring(fileName.lastIndexOf(".") + 1);// 这种返回的是没有.的后缀名
    }
}
