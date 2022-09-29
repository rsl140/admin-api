package com.serve.api.comm.model;

import com.serve.api.comm.enums.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "返回类")
public class Result<T> {
    @ApiModelProperty(value = "返回代码，int 200表示成功，其他表示失败,403表示未登录或者登录过期")
    private int code;

    @ApiModelProperty(value = "返回错误信息")
    private String message;

    @ApiModelProperty(value = "返回值信息")
    private T data;


    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {

    }

    public static Result okResult(Object data) {
        return new Result(ErrorCode.OK.getCode(), ErrorCode.OK.getValueCn(), data);
    }

    public static Result failResult(ErrorCode errorCode) {
        return new Result(errorCode.getCode(), errorCode.getValueCn(), null);
    }

    public static Result failResult(ErrorCode errorCode, String errorMsg) {
        return new Result(errorCode.getCode(), errorMsg, null);
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }
}
