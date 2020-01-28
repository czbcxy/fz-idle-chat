package fz.idle.chat.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class MessageDetail implements Serializable {
    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = 6297604862067678805L;
    @ApiModelProperty("用户id")
    private String clientId;
    @ApiModelProperty("好友id")
    private String friendId;
    @ApiModelProperty("消息内容")
    private String detail;

}
