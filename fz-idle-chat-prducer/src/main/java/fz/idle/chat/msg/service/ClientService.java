package fz.idle.chat.msg.service;

import fz.idle.chat.param.ClientAllParam;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientAllVo;

public interface ClientService {
    ResponseResult<ClientAllVo> login(LogParam param);

    ResponseResult<String> register(ClientAllParam param);
}
