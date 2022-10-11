package com.serve.api.comm.utils;


import com.serve.api.comm.config.JwtHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ParamUtil {

    public static String HTTP = "http://";

    /**
     * 获取真实ip地址 通过阿帕奇代理的也能获取到真实ip
     *
     * @param
     * @return
     */
    public static String getRealIp(/*HttpServletRequest request*/) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static Integer getUserId() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object userId = request.getAttribute("userId");
        if (userId == null) {
            String token = request.getParameter("token");
//            String method = request.getMethod();
//            if(org.apache.commons.lang3.StringUtils.isNotEmpty(method) && method.equals("OPTIONS")){
//                return true;
//            }
            if (!StringUtils.isEmpty(token)) {
                Integer userId0 = JwtHelper.getUserId(token);
                if (userId0 != null) {
                    return userId0;
                }
            }
            return null;
        } else {
            return (Integer) userId;
        }
    }


    public static String getServerGateway() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return HTTP + request.getServerName() + ":" + request.getServerPort();
    }


    public static String getAppVersion() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String currentVersion = request.getParameter("appVersion");
        return currentVersion;
    }


}
