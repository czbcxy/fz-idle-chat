package fz.idle.chat.msg.controller;

import fz.idle.chat.param.ClientAllParam;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.msg.service.impl.ClientServiceImpl;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientAllVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户接口", tags = "用户接口", description = "用户接口")
@RequestMapping("client")
public class ClientController {
    @Autowired
    ClientServiceImpl clientService;

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseResult<ClientAllVo> login(@RequestBody LogParam param) {
        return clientService.login(param);
    }
    @ApiOperation("注册用户")
    @PostMapping("register")
    public ResponseResult<String> register(@RequestBody ClientAllParam param){
        return clientService.register(param);
    }
}
