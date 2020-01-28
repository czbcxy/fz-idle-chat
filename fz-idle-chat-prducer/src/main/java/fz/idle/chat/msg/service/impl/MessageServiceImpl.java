package fz.idle.chat.msg.service.impl;

import fz.idle.chat.msg.mapper.MessageMapper;
import fz.idle.chat.msg.param.MsgParam;
import fz.idle.chat.msg.service.MessageService;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.FriendsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper mapper;

    @Override
    public ResponseResult<List<FriendsVo>> getFriends(String clientId) {
        ResponseResult<List<FriendsVo>> result = new ResponseResult<>();
        List<FriendsVo> friends = mapper.getFriend(clientId);
        result.setData(friends);
        return result;
    }

    @Override
    public ResponseResult<String> send(MsgParam param) {
        ResponseResult<String> result = new ResponseResult<>();
        try {
            String msgId = UUID.randomUUID().toString();
            param.setMsgId(msgId);
            mapper.sendMsg(param);
            mapper.send(param);
            result.setMessage("消息发送成功!");
        }catch (Exception e){
            result.setCode("1111");
            result.setMessage("消息发送失败,错误信息:" +e.getMessage());
        }
        return result;
    }
}
