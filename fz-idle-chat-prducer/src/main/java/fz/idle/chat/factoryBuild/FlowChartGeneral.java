package fz.idle.chat.factoryBuild;

import com.alibaba.fastjson.JSON;
import fz.idle.chat.entry.MetaData;
import fz.idle.chat.enums.Types;
import fz.idle.chat.msg.service.ClientService;
import fz.idle.chat.msg.service.MessageService;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientAllVo;
import fz.idle.chat.msg.vo.FriendsVo;
import fz.idle.chat.param.LogParam;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FlowChartGeneral {
    private ChannelHandlerContext ctx;
    private MetaData msg;

    public void refresh(ChannelHandlerContext ctx, Object msg) {
        this.ctx = ctx;
        this.msg = (MetaData) msg;
        String type = ((MetaData) msg).getType();
        if (Objects.equals(type, Types.login.name())) {
            queryFriend(login());
        } else {
            doChat();
        }
    }

    public ResponseResult<ClientAllVo> login() {
        ClientService user = ActionFactoryBuild.getClient(ClientService.class.getSimpleName());
        LogParam param = (LogParam) msg.getDate();
        ResponseResult<ClientAllVo> login = user.login(param);
        //登录成功
        if (Objects.isNull(login) || !Objects.equals(login.getCode(), "0000")) {
            ctx.write("登錄失敗");
            ctx.flush();
            ctx.close();
        }
        ctx.write("登錄成功");
        ctx.flush();
        return login;
    }

    public void queryFriend(ResponseResult<ClientAllVo> login) {
        //查询成功
        MessageService messageService = ActionFactoryBuild.getMessage(MessageService.class.getSimpleName());
        ResponseResult<List<FriendsVo>> friends = messageService.getFriends(login.getData().getClientId());
        if (!Objects.equals(friends.getCode(), "0000")) {
            ctx.write("查詢好友失敗");
            ctx.flush();
            ctx.close();
        }
        String jsonStr = JSON.toJSONString(friends);
        ctx.write(jsonStr);
        ctx.flush();
    }

    //循环聊天
    public void doChat() {
        //循环聊天
        for (int i = 0; i < 10; i++) {
            ctx.write("链接成功" + i);
            ctx.flush();
        }
    }
}
