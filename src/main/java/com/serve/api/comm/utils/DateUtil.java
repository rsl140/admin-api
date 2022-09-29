package com.serve.api.comm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {


    // 各种时间格式
    public static final SimpleDateFormat yyyy = new SimpleDateFormat(
            "yyyy");
    // 各种时间格式
    public static final SimpleDateFormat yyyyMM = new SimpleDateFormat(
            "yyyyMM");
    public static final SimpleDateFormat MMdd = new SimpleDateFormat(
            "MM-dd");
    // 各种时间格式
    public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
            "yyyyMMdd");

    // 各种时间格式
    public static final SimpleDateFormat date_sdf_wz() {
        return new SimpleDateFormat("yyyy年MM月dd日");
    }

    public static final SimpleDateFormat yyyy_MM_dd() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static final SimpleDateFormat yyyy_MM_dd_HH_mm() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public static final SimpleDateFormat mmddhhmmss = new SimpleDateFormat(
            "MMddHHmmss");
    public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
            "HH:mm");
    public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static String date2String(Date date, SimpleDateFormat sdf) {
        return sdf.format(date);
    }

    public static String date2String(Date date) {
        return yyyy_MM_dd_HH_mm_ss.format(date);
    }

//    public static SimpleDateFormat getSimpleDateFormat_yyyy_MM_dd_HH_mm_ss(){
//        return new SimpleDateFormat(DateUtil.yyyy_MM_dd_HH_mm_ss);
//    }

    // 01. java.util.Date --> java.time.LocalDateTime
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    // 02. java.util.Date --> java.time.LocalDate
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    // 03. java.util.Date --> java.time.LocalTime
    public static LocalTime dateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
        return localTime;
    }


    // 04. java.time.LocalDateTime --> java.util.Date
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }


    // 05. java.time.LocalDate --> java.util.Date
    public static Date LocalDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    // 06. java.time.LocalTime --> java.util.Date
    public static Date localTimeToDate(LocalTime localTime) {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static Date getTodayBeginTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    public static Date getDayEndTime() {
        return getDayEndTime(null);
    }

    public static Date getDayEndTime(Date anchorDate) {
        Calendar calendar = Calendar.getInstance();
        if (null != anchorDate) {
            calendar.setTime(anchorDate);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getDayBeginTime() {
        return getDayBeginTime(null);
    }

    public static Date getDayBeginTime(Date anchorDate) {
        Calendar calendar = Calendar.getInstance();
        if (null != anchorDate) {
            calendar.setTime(anchorDate);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    public static Date getMonthBefore(int month) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH,-month);
//        calendar.set(Calendar.HOUR_OF_DAY,00);
//        calendar.set(Calendar.MINUTE,00);
//        calendar.set(Calendar.SECOND,00);
        return getMonthBefore(month, null);
    }

    public static Date getMonthBefore(int month, Date anchorDate) {
        Calendar calendar = Calendar.getInstance();
        if (null != anchorDate) {
            calendar.setTime(anchorDate);
        }
        calendar.add(Calendar.MONTH, -month);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    public static Date getHourBefore(int hour) {
        return getHourBefore(hour, null);
    }

    public static Date getHourBefore(int hour, Date anchorDate) {
        Calendar calendar = Calendar.getInstance();
        if (null != anchorDate) {
            calendar.setTime(anchorDate);
        }
        calendar.add(Calendar.HOUR_OF_DAY, -hour);
        return calendar.getTime();
    }

//    public static Integer getGapHour(Date date1, Date date2) {
//        Integer datemill1 = date1.getTime();
//        Integer datemill2 = date2.getTime();
//        Integer gapHour = (datemill2 - datemill1) / (60 * 60 * 1000L);
//        return Math.abs(gapHour);
//    }


    public static Date string2Date(String date, SimpleDateFormat sdf) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date string2Date(String date) {
        try {
            return yyyy_MM_dd_HH_mm_ss.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getMondayDate(int i) {
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.DAY_OF_WEEK) == 1) {
            now.add(Calendar.DAY_OF_WEEK, -1);
        }
        if (i != 0) {
            now.add(Calendar.DAY_OF_WEEK, i * 7);
        }
        now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }


    public static Date getSundayDate(int i) {
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.DAY_OF_WEEK) == 1) {
            now.add(Calendar.DAY_OF_WEEK, -1);
        }
        if (i != 0) {
            now.add(Calendar.DAY_OF_WEEK, i * 7);
        }
        now.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        now.add(Calendar.DAY_OF_WEEK, 1);
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getMonthEndDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateByMonthDay(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getMinutesAfter(int minutes, Date startDate) {
        int seconds = minutes * 60;
        return getSecondsAfter(seconds, startDate);
    }

    public static Date getMinutesAfter(int minutes) {
        return getMinutesAfter(minutes, new Date());
    }

    public static Date getMinutesBefore(int minutes) {
        return getMinutesAfter(-minutes, new Date());
    }

    public static Date getMinutesBefore(int minutes, Date startDate) {
        return getMinutesAfter(-minutes, startDate);
    }

    public static Date getSecondsAfter(int seconds, Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static Date getSecondsAfter(int seconds) {
        return getSecondsAfter(seconds, new Date());
    }
}
