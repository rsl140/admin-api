package com.serve.api.comm.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
@Slf4j
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> cla) {
        return applicationContext.getBean(cla);
    }

    public static <T> T getBean(String name, Class<T> cal) {
        return applicationContext.getBean(name, cal);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }


    public static String getProperty(String key) {
        return applicationContext.getBean(Environment.class).getProperty(key);
    }

    /**
     * 国际化使用
     */

    public static String getMessage(String key, Locale locale) {
        return applicationContext.getMessage(key, null, locale);
    }

    /**
     * 获取当前环境
     */
    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    public static boolean isPrd() {
        String activeProfile = getActiveProfile();
        return "prd".equals(activeProfile) ? true : false;
    }

    public static HttpServletRequest getCurrentThreadRequest() {
        // 获取到当前线程绑定的请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取当前请求的语言信息
     *
     * @return
     */
    public static Locale getRequestLocal() {
        HttpServletRequest request = getCurrentThreadRequest();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale local = localeResolver == null ? Locale.getDefault() : localeResolver.resolveLocale(request);
        return local;
    }

    /**
     * 判断是否是手机
     *
     * @return
     */
    public static boolean isPhone(String tel) {
        if (tel != null && (!tel.isEmpty())) {
            return Pattern.matches("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(16[5,6])|(17[0-8])|(18[0-9])|(19[1、5、8、9]))\\d{8}$", tel);
        }
        return true;
    }

    /**
     * 判断是否是邮箱
     *
     * @return
     */
    public static boolean isEmail(String email) {
        if (email != null && (!email.isEmpty())) {
            return Pattern.matches("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", email);
        }
        return true;
    }

}