package fz.idle.chat.msg.controller;

import fz.idle.chat.param.AddFriendParam;
import fz.idle.chat.param.ClientAllParam;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.msg.service.impl.ClientServiceImpl;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientAllVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "用户接口", tags = "用户接口", description = "用户接口")
@RequestMapping("client")
public class ClientController {
    @Autowired
    ClientServiceImpl clientService;

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseResult login(@RequestBody LogParam param) {
        return clientService.login(param);
    }

    @ApiOperation("注册用户")
    @PostMapping("register")
    public ResponseResult register(@RequestBody ClientAllParam param) {
        return clientService.register(param);
    }

    @ApiOperation("添加好友")
    @PostMapping("addFriend")
    public ResponseResult addFriend(@RequestBody AddFriendParam param){
        return clientService.addFriend(param);
    }

    @ApiOperation("查询好友")
    @PostMapping("findFriend")
    public ResponseResult findFriend(@ApiParam(value = "好友账号",required = true) @RequestParam String account){
        return clientService.findFriend(account);
    }
}
