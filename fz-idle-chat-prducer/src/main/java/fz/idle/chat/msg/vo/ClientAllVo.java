package fz.idle.chat.msg.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ClientAllVo {
    @ApiModelProperty("用戶id")
    private String clientId;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("性别")
    private String gender;
}
