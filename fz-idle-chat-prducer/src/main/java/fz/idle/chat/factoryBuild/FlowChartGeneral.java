package fz.idle.chat.factoryBuild;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.entry.MetaData;
import fz.idle.chat.enums.Types;
import fz.idle.chat.msg.service.ClientService;
import fz.idle.chat.msg.service.MessageService;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.param.MsgParam;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FlowChartGeneral {
    private ChannelHandlerContext ctx;

    @Autowired
    private ClientService clientService;
    @Autowired
    private MessageService messageService;

    public void refresh(ChannelHandlerContext ctx, Object msg) {
        this.ctx = ctx;
        String type = ((MetaData) msg).getType();
        if (Objects.equals(type, Types.login.name())) {
            queryFriend(login((MetaData) msg));
        } else {
            doChat((MetaData) msg);
        }
    }

    public ResponseResult login(MetaData msg) {
        LogParam param = (LogParam) msg.getDate();
        ResponseResult login = clientService.login(param);
        //登录成功
        if (Objects.isNull(login) || !Objects.equals(login.getCode(), "0000")) {
            ctx.writeAndFlush("登錄失敗");
            ctx.close();
        }
        ctx.writeAndFlush("返回给客户端登錄成功。。。");
        return login;
    }

    public void queryFriend(ResponseResult login) {
        //查询成功
        JSONObject jsonObject = JSON.parseObject(login.getData());
        ResponseResult friends = messageService.getFriends(String.valueOf(jsonObject.get("clientId")));
        if (!Objects.equals(friends.getCode(), "0000")) {
            ctx.writeAndFlush("返回给客户端查詢好友失敗");
            ctx.close();
        }
        String jsonStr = JSON.toJSONString(friends);
        ctx.writeAndFlush("查询到的好友信息：" + jsonStr);
    }

    //循环聊天
    public void doChat(MetaData msg) {
        if (Objects.isNull(ctx)) {
            return;
        }
        MessageDetail date = (MessageDetail) msg.getDate();
        MsgParam param = new MsgParam();
        param.setClientId(date.getClientId());
        param.setDetail(date.getDetail());
        param.setFriendId(date.getFriendId());
        messageService.send(param);
        ctx.writeAndFlush("返回给客户消息。。。hello world。。。" + msg.toString());
    }
}
