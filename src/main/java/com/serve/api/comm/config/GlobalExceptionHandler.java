package com.serve.api.comm.config;

import com.serve.api.comm.enums.ErrorCode;
import com.serve.api.comm.model.BusinessException;
import com.serve.api.comm.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/***
 * @ExceptionHandler(RuntimeException.class) 是抓取全局错误。
 * 接口 ErrorController  是404 505时候会默认调用 getErrorPath(),我们将这个方式实现转到一个控制器。本例是转到接口 error
 */
//@ControllerAdvice
//@RestController
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements AsyncConfigurer {
    //处理所有异常
    @ExceptionHandler
    public Result exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String errorMsg = "";
        String errorData;
        ErrorCode errorCode = null;
        String customMsg = "";
//        String token = request.getParameter("token");
        if (e != null) {
            if (e instanceof BusinessException) {
                errorCode = ((BusinessException) e).getErrorCode();
//                errorData = "from:" + request.getRequestURI() + ";errorMsg:" + errorMsg;
//                log.error("errorData:{},errorCode:{},token:{}", errorData,  errorCode,token);
//                return Result.failResult(errorCode);
            } else if (e instanceof org.springframework.web.bind.MissingServletRequestParameterException
                    || e instanceof javax.validation.ConstraintViolationException
//                    || e instanceof BindException
                    || e instanceof ServletRequestBindingException
                    || e instanceof org.springframework.web.method.annotation.MethodArgumentTypeMismatchException) {
                errorCode = ErrorCode.E_PARAM;
//                errorData = "from:" + request.getRequestURI() + ";errorMsg:" + errorMsg;
//                log.error("errorData:{},Exception e:{}", errorData, e);
//                return Result.failResult(ErrorCode.E_PARAM);
            } else {
//                if (e instanceof org.apache.catalina.connector.ClientAbortException) {
//                    smsService.sendVerifyCodeAscy("18200428182","1111",Constant.CHINA_REGION);
//                }
                errorMsg = e.toString();
                if (!StringUtils.isEmpty(errorMsg)) {
                    if (errorMsg.length() > 2000) {
                        errorMsg = errorMsg.substring(0, 2000);
                    }
                }
            }
        }
        Integer code = null;
        if (errorCode == null) {
            Object codeObj = request.getAttribute("javax.servlet.error.status_code");
//            code = ErrorCode.E_SERVER.getCode();
            if (codeObj != null) {
                code = (Integer) codeObj;
            }
            if (code != null && code == ErrorCode.E_NOT_FOUND.getCode()) {
                errorCode = ErrorCode.E_NOT_FOUND;
            } else {
                errorCode = ErrorCode.E_SERVER;
            }
        }
        String fromUrl = request.getRequestURI();
        errorData = "from:" + fromUrl + ";code:" + code + ";errorCode：" + errorCode.name() + ";errorMsg:" + errorMsg;
        if (errorCode == ErrorCode.E_PARAM || errorCode == ErrorCode.E_SERVER
                || errorCode == ErrorCode.E_NOT_FOUND) {
//            log.error("errorData:{},token:{},Exception e:{}", errorData, token, e);
            log.error("errorData:{},params:{},Exception e:{}", errorData, Constant.gson.toJson(request.getParameterMap()), e);
        } else {
//            log.error("errorData:{},token:{}", errorData, token);
            log.error("errorData:{},params:{}", errorData, Constant.gson.toJson(request.getParameterMap()));
        }
//        log.error("params:{}",Constant.gson.toJson(request.getParameterMap()));
        if (ErrorCode.CUSTOM_ERROR_MSG == errorCode) {
            log.info("原因：{}", e.getMessage());
            return Result.failResult(errorCode, e.getMessage());
        } else if (!StringUtils.isEmpty(customMsg)) {
            return Result.failResult(errorCode).message(customMsg);
        } else {
            return Result.failResult(errorCode);
        }
    }


    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                if (throwable instanceof BusinessException) {
                    BusinessException businessException = (BusinessException) throwable;
                    log.error("异步任务错误捕捉，Method name:{}，ErrorCode:{}", method.getName(), businessException.getErrorCode());
                } else {
                    log.error("异步任务错误捕捉，Method name:{}，Message:{},原因e:{}", method.getName(), throwable.getMessage(), throwable);
                }
                for (Object param : objects) {
                    log.info("Parameter value ------ " + param);
                }
            }
        };
    }

}