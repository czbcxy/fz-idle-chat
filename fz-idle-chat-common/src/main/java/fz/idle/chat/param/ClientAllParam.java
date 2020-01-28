package fz.idle.chat.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClientAllParam {
    @ApiModelProperty(value = "用户id", hidden = true)
    private String clientId;
    @ApiModelProperty(value = "账号", required = true)
    private String account;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty("性别")
    private int gender;
}
