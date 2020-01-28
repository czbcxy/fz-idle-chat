package fz.idle.chat.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LogParam {
    @ApiModelProperty(value = "账号",required = true)
    private String account;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
