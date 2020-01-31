package fz.idle.chat.factoryBuild;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatProviderHandler extends ChannelInboundHandlerAdapter {

    private FlowChartGeneral flowChartGeneral;

    public ChatProviderHandler() {
        flowChartGeneral = new FlowChartGeneral();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        flowChartGeneral.refresh(ctx, msg);
    }

    /**
     * 当有客户端连接时，handlerAdded会执行,就把该客户端的通道记录下来，加入队列
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();//获得客户端通道
        //通知其他客户端有新人进入
        for (Channel channel : FriendContent.channels) {
            if (channel != inComing) {
                channel.writeAndFlush("[欢迎: " + inComing.remoteAddress() + "] 进入聊天室！\n");
            }
        }
        //加入队列
        FriendContent.channels.add(inComing);
    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel outComing = ctx.channel();//获得客户端通道
        //通知其他客户端有人离开
        for (Channel channel : FriendContent.channels) {
            if (channel != outComing) {
                channel.writeAndFlush("[再见: ]" + outComing.remoteAddress() + " 离开聊天室！\n");
            }
        }
        FriendContent.channels.remove(outComing);
    }

    /**
     * 每当从客户端有消息写入时
     *
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        Channel inComing = channelHandlerContext.channel();
        for (Channel channel : FriendContent.channels) {
            if (channel != inComing) {
                channel.writeAndFlush("[用户" + inComing.remoteAddress() + " 说：]" + msg + "\n");
            } else {
                channel.writeAndFlush("[我说：]" + msg + "\n");
            }
        }
    }

    /**
     * 当服务器监听到客户端活动时
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        System.out.println("[" + inComing.remoteAddress() + "]: 在线");
    }

    /**
     * 离线
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        System.out.println("[" + inComing.remoteAddress() + "]: 离线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.fillInStackTrace());
        Channel inComing = ctx.channel();
        System.out.println(inComing.remoteAddress() + "通讯异常！");
        ctx.close();
    }
}
