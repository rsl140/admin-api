package com.serve.api.comm.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class Constant {
    /**
     * 维护重启开关
     **/
    public static boolean isOff = false;
    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    public static int NONE_ID = 0;

    public static String USER_ID_KEY = "userId";

    public static String SPLIT_KEY = ",";
    //    public static String SPLIT_KEY_VERTICAL = "\\|";
    public static String DOT = "\\.";

    public static JsonParser jsonParser = new JsonParser();

    public static String CHINA_REGION = "+86";
    public static String M3U8 = "m3u8";
    public static Random random = new Random();
    public static String NEW_MUMBER_AUTHORITY_VERSION = "1.3.9";

    public static BigDecimal F2MRate = BigDecimal.valueOf(1);
    public static String BUSY_VIDEOCHAT = "VIDEOCHAT";


    public static Date START_TIME = new Date();//项目启动时间
    public static BigDecimal MIN = BigDecimal.valueOf(0.01);//最小值

    public static int LIVE_OFFSET = 0;

    public static String WECHAT_LOGIN_NEW_VERSION = "";
    public static String SPLIT = ",";

    public static int DELAY_TASK_RELAX_SECONDS = 60;

}
