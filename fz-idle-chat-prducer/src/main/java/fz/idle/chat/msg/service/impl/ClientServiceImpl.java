package fz.idle.chat.msg.service.impl;

import com.alibaba.fastjson.JSONObject;
import fz.idle.chat.msg.mapper.ClientMapper;
import fz.idle.chat.param.AddFriendParam;
import fz.idle.chat.param.ClientAllParam;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.msg.service.ClientService;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientAllVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientMapper mapper;

    @Override
    public ResponseResult login(LogParam param) {
        ResponseResult result = new ResponseResult();
        ClientAllVo login = mapper.login(param);
        result.setData(JSONObject.toJSONString(login));
        if (login == null){
            result.setCode("1111");
            result.setMessage("用户不存在或用户名或密码错误!");
        }
        return result;
    }

    @Override
    public ResponseResult register(ClientAllParam param) {
        param.setClientId(UUID.randomUUID().toString());
        ResponseResult result = new ResponseResult();
        try {
            mapper.saveClient(param);
            result.setMessage("注册成功!");
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("注册失败,错误信息:" +e.getMessage());
            result.setCode("1111");
        }
        return result;
    }

    @Override
    public ResponseResult addFriend(AddFriendParam param) {
        ResponseResult result = new ResponseResult();
        try {
            mapper.addFriend(param);
            result.setMessage("添加好友成功!");
        }catch (Exception e){
            result.setMessage("添加好友失败,错误信息: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult findFriend(String account) {
        ClientAllVo friend = mapper.findFriend(account);
        ResponseResult result = new ResponseResult();
        result.setData(JSONObject.toJSONString(friend));
        if (friend == null){
            result.setMessage("没有查询到好友!");
        }
        return result;
    }
}
