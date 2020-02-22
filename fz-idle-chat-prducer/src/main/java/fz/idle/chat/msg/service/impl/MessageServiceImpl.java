package fz.idle.chat.msg.service.impl;

import com.alibaba.fastjson.JSONObject;
import fz.idle.chat.msg.mapper.MessageMapper;
import fz.idle.chat.param.AddFriendParam;
import fz.idle.chat.param.FindMsgParam;
import fz.idle.chat.param.MsgParam;
import fz.idle.chat.msg.service.MessageService;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.FriendsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper mapper;

    @Override
    public ResponseResult getFriends(String clientId) {
        ResponseResult result = new ResponseResult();
        List<FriendsVo> friends = mapper.getFriend(clientId);
        result.setData(JSONObject.toJSONString(friends));
        if (friends == null){
            result.setMessage("没有查询到好友!");
        }
        return result;
    }

    @Override
    public ResponseResult send(MsgParam param) {
        ResponseResult result = new ResponseResult();
        try {
            AddFriendParam addFriendParam = new AddFriendParam();
            addFriendParam.setClientId(param.getClientId());
            addFriendParam.setFriendId(param.getFriendId());
            int count = mapper.selectFriendById(addFriendParam);
            if (count < 1){
                result.setCode("9999");
                result.setMessage("请先添加对方为好友!");
                return result;
            }
            String msgId = UUID.randomUUID().toString();
            param.setMsgId(msgId);
            mapper.sendMsg(param);
            mapper.send(param);
            result.setMessage("消息发送成功!");
        }catch (Exception e){
            result.setCode("1111");
            result.setMessage("消息发送失败,错误信息:" +e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResponseResult findMsg(FindMsgParam param) {
        if (param.getPageSize() == null || param.getPageSize() < 1){
            param.setPageSize(10);
        }
        if (param.getCurrentPage() == null || param.getCurrentPage() < 1){
            param.setCurrentPage(0);
        }else {
            param.setCurrentPage(param.getCurrentPage() - 1);
        }
        ResponseResult result = new ResponseResult();
        List<HashMap<String,String>> data = mapper.findMsg(param);
        result.setData(JSONObject.toJSONString(data));
        return result;
    }
}
