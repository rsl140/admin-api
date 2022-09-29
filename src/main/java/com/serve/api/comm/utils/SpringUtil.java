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

}