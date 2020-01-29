package fz.idle.chat.msg.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseResult {
    @ApiModelProperty("状态码")
    private String code = "0000";
    @ApiModelProperty("提示信息")
    private String message = null;
    @ApiModelProperty("返回数据")
    private String data = null;

}
