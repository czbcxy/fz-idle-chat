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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientMapper mapper;

    /**
     * 登录接口
     *
     * @param param
     * @return
     */
    @Override
    public ResponseResult login(LogParam param) {
        ResponseResult result = new ResponseResult();
        ClientAllVo login = mapper.login(param);
        result.setData(JSONObject.toJSONString(login));
        if (login == null) {
            result.setCode("1111");
            result.setMessage("用户不存在或用户名或密码错误!");
        }
        return result;
    }

    /**
     * 注册接口
     *
     * @param param
     * @return
     */
    @Override
    public ResponseResult register(ClientAllParam param) {
        param.setClientId(UUID.randomUUID().toString());
        ResponseResult result = new ResponseResult();
        try {
            mapper.saveClient(param);
            result.setMessage("注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("注册失败,错误信息:" + e.getMessage());
            result.setCode("1111");
        }
        return result;
    }

    /**
     * 添加好友接口
     *
     * @param param
     * @return
     */
    @Override
    public ResponseResult addFriend(AddFriendParam param) {
        ResponseResult result = new ResponseResult();
        try {
            HashMap<Object, Object> map = new HashMap<>(10);
            Integer friendId = mapper.addFriend(param);
            map.put("friendId", friendId);
            ClientAllVo vo = mapper.clientByCid(param.getClientId());
            ArrayList<Object> list = new ArrayList<>();
            list.add(map);
            list.add(vo);
            result.setData(JSONObject.toJSONString(list));
            result.setMessage(vo.getNickName() + "请求添加 client_id 为 : " + param.getFriendId() + " 的用户为好友!");
        } catch (Exception e) {
            result.setMessage("添加好友失败,错误信息: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult deleteFriend(AddFriendParam param) {
        ResponseResult result = new ResponseResult();
        try {
            deleteFrd(param);
            result.setMessage("删除好友成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode("2222");
            result.setMessage("删除好友失败!");
        }
        return result;
    }

    void deleteFrd(AddFriendParam param) {
        mapper.delete(param);
        mapper.delete1(param);
        mapper.deleteMsg(param);
        mapper.deleteMsg1(param);
    }

    /**
     * 根据对方账号查询
     *
     * @param account
     * @return
     */
    @Override
    public ResponseResult findFriend(String account) {
        ClientAllVo friend = mapper.findFriend(account);
        ResponseResult result = new ResponseResult();
        result.setData(JSONObject.toJSONString(friend));
        if (friend == null) {
            result.setMessage("没有查询到好友!");
        }
        return result;
    }

    @Override
    public ResponseResult accept(String friendId) {
        ResponseResult result = new ResponseResult();
        mapper.accept(friendId);
        result.setMessage("添加好友成功!");
        return result;
    }
}
