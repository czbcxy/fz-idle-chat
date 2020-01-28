package fz.idle.chat.msg.util;

import io.swagger.annotations.ApiModelProperty;

public class ResponseResult<T> {
    @ApiModelProperty("状态码")
    private String code = "0000";
    @ApiModelProperty("提示信息")
    private String message = null;
    @ApiModelProperty("返回数据")
    private T data = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult() {
    }
}
