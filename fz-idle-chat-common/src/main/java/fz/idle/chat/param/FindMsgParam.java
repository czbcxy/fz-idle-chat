package fz.idle.chat.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FindMsgParam {
    @ApiModelProperty(value = "用户id",required = true)
    private String clientId;
    @ApiModelProperty(value = "好友id",required = true)
    private String friendId;
    @ApiModelProperty(value = "每页信息条数",example = "10",required = true)
    private Integer pageSize;
    @ApiModelProperty(value = "当前页",example = "1",required = true)
    private Integer currentPage;
}
