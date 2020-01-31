package fz.idle.chat.factoryBuild;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.entry.MetaData;
import fz.idle.chat.enums.Types;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientVo;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.param.MsgParam;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FlowChartGeneral {

    private ChannelHandlerContext ctx;

    public void refresh(ChannelHandlerContext ctx, Object msg) {
        this.ctx = ctx;
        String type = ((MetaData) msg).getType();
        if (Objects.equals(type, Types.login.name())) {
            queryFriend(login((MetaData) msg));
        } else {
            doChat((MetaData) msg);
        }
    }

    public String login(MetaData msg) {
        LogParam param = (LogParam) msg.getDate();
        ResponseResult login = BuildFactory.getClientService().login(param);
        //登录成功
        if (Objects.isNull(login) || !Objects.equals(login.getCode(), "0000")) {
            ctx.writeAndFlush("登錄失敗");
            ctx.close();
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(login.getData());
        String clientId = String.valueOf(jsonObject.get("clientId"));
        return clientId;
    }

    public void queryFriend(String clientId) {
        if (Objects.isNull(clientId)) {
            return;
        }
        //查询成功
        ResponseResult friends = BuildFactory.getMessageService().getFriends(clientId);
        if (!Objects.equals(friends.getCode(), "0000")) {
            ctx.writeAndFlush("返回给客户端查詢好友失敗");
            ctx.close();
            return;
        }
        ctx.writeAndFlush("user:" + clientId);
        ctx.writeAndFlush("查询到的好友信息：" + friends);
        //将好友存储给对应用户
        HashMap<String, ClientVo> friendsMap = new HashMap<>();
        JSONArray jsonArray = JSON.parseArray(friends.getData());
        for (Object friend : jsonArray) {
            ClientVo clientVo = JSON.parseObject(friend.toString(), ClientVo.class);
            friendsMap.put(clientVo.getClientId(), clientVo);
        }
        FriendContent.MAP_CHANNEL.put(clientId, ctx.channel());
        FriendContent.MAP_FIRENDS.put(ctx.channel(), friendsMap);
    }

    //循环聊天
    public void doChat(MetaData msg) {
        if (Objects.isNull(ctx)) {
            return;
        }
        //存储库
        MessageDetail date = (MessageDetail) msg.getDate();

        //向客户发送消息
        Map<String, ClientVo> map = FriendContent.MAP_FIRENDS.get(ctx.channel());
        ClientVo clientVo = map.get(date.getFriendId());
        if (Objects.isNull(clientVo)) {
            ctx.writeAndFlush("返回给客户消息。。。您没有这个好友。");
            return;
        }
        Channel channel = FriendContent.MAP_CHANNEL.get(clientVo.getClientId());
        if (Objects.isNull(channel)) {
            ctx.writeAndFlush("返回给客户消息。。。当前好友不在线。");
            return;
        }

        //存库
        MsgParam param = new MsgParam();
        param.setClientId(date.getClientId());
        param.setDetail(date.getDetail());
        param.setFriendId(date.getFriendId());
        BuildFactory.getMessageService().send(param);
        channel.writeAndFlush("返回给客户消息。。。hello world。。。" + date.getDetail());
    }

}
