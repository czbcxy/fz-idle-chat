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
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FlowChartGeneral {
    private ChannelHandlerContext ctx;
    private static MessageService messageService = null;

    public void refresh(ChannelHandlerContext ctx, Object msg) {
        this.ctx = ctx;
        String type = ((MetaData) msg).getType();
        if (Objects.equals(type, Types.login.name())) {
            queryFriend(login((MetaData)msg));
        } else {
            doChat((MetaData) msg);
        }
    }

    public ResponseResult login(MetaData msg) {
        ClientService user = ActionFactoryBuild.getClient(ClientService.class.getSimpleName());
        LogParam param = (LogParam) msg.getDate();
        ResponseResult login = user.login(param);
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
        messageService = ActionFactoryBuild.getMessage(MessageService.class.getSimpleName());
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
        Object date = msg.getDate();
        System.out.println(date);
        MsgParam param = new MsgParam();
        BeanUtils.copyProperties(date,param);
        messageService.send(param);
        ctx.writeAndFlush("返回给客户消息。。。hello world。。。" + msg.toString());
    }
}
