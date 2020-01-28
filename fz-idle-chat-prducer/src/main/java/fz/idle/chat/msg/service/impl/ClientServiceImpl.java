package fz.idle.chat.msg.service.impl;

import fz.idle.chat.msg.mapper.ClientMapper;
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
    public ResponseResult<ClientAllVo> login(LogParam param) {
        ResponseResult<ClientAllVo> result = new ResponseResult<>();
        result.setData(mapper.login(param));
        return result;
    }

    @Override
    public ResponseResult<String> register(ClientAllParam param) {
        param.setClientId(UUID.randomUUID().toString());
        ResponseResult<String> result = new ResponseResult<>();
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
}
