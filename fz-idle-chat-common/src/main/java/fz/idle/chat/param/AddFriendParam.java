package fz.idle.chat.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddFriendParam {
    @ApiModelProperty("用户id")
    private String clientId;
    @ApiModelProperty("好友id")
    private String friendId;
}
