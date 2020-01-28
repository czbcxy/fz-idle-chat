package fz.idle.chat.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LogParam implements Serializable {
    @ApiModelProperty(value = "账号",required = true)
    private String account;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
