package fz.idle.chat.msg.service;

import fz.idle.chat.msg.param.MsgParam;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.FriendsVo;

import java.util.List;

public interface MessageService {
    ResponseResult<List<FriendsVo>> getFriends(String clientId);

    ResponseResult<String> send(MsgParam param);
}
