package fz.idle.chat.msg.param;

import fz.idle.chat.entry.MessageDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MsgParam extends MessageDetail {
    @ApiModelProperty("消息id")
    private String msgId;
}
