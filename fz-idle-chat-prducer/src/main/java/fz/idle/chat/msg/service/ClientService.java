package fz.idle.chat.msg.service;

import fz.idle.chat.param.AddFriendParam;
import fz.idle.chat.param.ClientAllParam;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientAllVo;

public interface ClientService {
    ResponseResult login(LogParam param);

    ResponseResult register(ClientAllParam param);

    ResponseResult addFriend(AddFriendParam param);

    ResponseResult deleteFriend(AddFriendParam param);

    ResponseResult findFriend(String account);

    ResponseResult accept(String friendId);
}
