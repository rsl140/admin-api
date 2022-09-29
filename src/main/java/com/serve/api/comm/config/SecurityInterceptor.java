package com.serve.api.comm.config;

import com.serve.api.comm.enums.ErrorCode;
import com.serve.api.comm.model.BusinessException;
import com.serve.api.comm.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 权限拦截器
 * WebMvcConfigurerAdapter 已经被抛弃，直接用WebMvcConfigurer 用于配置拦截器的，重写其方法addInterceptors，注册当前启用的拦截器，配置需要拦截的和例外
 * HandlerInterceptor-- 拦截器，定义拦截方式，如preHandle，并重写拦截方法，定义拦截业务
 */
@Slf4j
@Component
@Configuration
public class SecurityInterceptor implements WebMvcConfigurer, HandlerInterceptor {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径和排除拦截路径
        // 拦截的路径
        registry.addInterceptor(this)
                .addPathPatterns("/api/**")
                // 不拦截的路径
                .excludePathPatterns("/**/vt/**", "/**/test/**");
    }

    /**
     * 开启线程
     */
    static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!SpringUtil.isPrd()) {
            threadLocal.set(System.currentTimeMillis());
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        String token = request.getParameter("token");
        String token2 = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            throw BusinessException.instance(ErrorCode.INVALID_TOKEN);
        } else {
            Integer userId = JwtHelper.getUserId(token);
            request.setAttribute("userId", userId);
            return true;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!SpringUtil.isPrd()) {
            log.info("接口：{},服务端响应时间：{}毫秒", request.getRequestURI(), System.currentTimeMillis() - threadLocal.get());
        }
    }

    /**
     * 这种设置，报500的时候还是存在跨域问题，
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许证书 不再默认开启
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600)
        ;
    }

}