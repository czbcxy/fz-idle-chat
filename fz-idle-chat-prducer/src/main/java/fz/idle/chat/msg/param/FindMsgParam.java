package fz.idle.chat.msg.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FindMsgParam {
    @ApiModelProperty("用户id")
    private String clientId;
    @ApiModelProperty("好友id")
    private String friendId;

}
