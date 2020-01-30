package fz.idle.chat.factoryBuild;

import fz.idle.chat.msg.vo.ClientVo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class FriendContent {

    public static final Map<Channel,ChannelHandlerContext> ctxs = new ConcurrentHashMap<>();

    //在线人数
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //好友圈
    public static final Map<Channel, Map<String, ClientVo>> MAP_FIRENDS = new ConcurrentHashMap();

    //好友id和channal绑定
    public static final Map<String, Channel> MAP_CHANNEL = new ConcurrentHashMap();
}
