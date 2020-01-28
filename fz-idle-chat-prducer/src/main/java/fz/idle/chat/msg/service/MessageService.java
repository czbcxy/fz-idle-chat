package fz.idle.chat.msg.service;

import fz.idle.chat.param.MsgParam;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.FriendsVo;

import java.util.List;

public interface MessageService {
    ResponseResult getFriends(String clientId);

    ResponseResult send(MsgParam param);
}
