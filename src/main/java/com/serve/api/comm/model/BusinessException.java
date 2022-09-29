package com.serve.api.comm.model;


import com.serve.api.comm.enums.ErrorCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "异常类")
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public static BusinessException instance(ErrorCode errorCode) {
        return new BusinessException(errorCode);
    }

    public BusinessException(ErrorCode errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public static BusinessException instance(ErrorCode errorCode, String errorMsg) {
        return new BusinessException(errorCode, errorMsg);
    }
}
