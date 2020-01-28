package fz.idle.chat.msg.controller;

import fz.idle.chat.param.MsgParam;
import fz.idle.chat.msg.service.impl.MessageServiceImpl;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.FriendsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("msg")
@Api(tags = "聊天接口" , value = "聊天接口",description = "聊天接口")
public class MessageController {
    @Autowired
    private MessageServiceImpl service;
    @ApiOperation("获取用户好友列表")
    @PostMapping("getFriends")
    public ResponseResult getFriends(@ApiParam(value = "用户id",required = true)@RequestParam String clientId){
        return service.getFriends(clientId);
    }
    @ApiOperation("发送消息")
    @PostMapping("send")
    public ResponseResult send(@RequestBody MsgParam param){
        return service.send(param);
    }

}
