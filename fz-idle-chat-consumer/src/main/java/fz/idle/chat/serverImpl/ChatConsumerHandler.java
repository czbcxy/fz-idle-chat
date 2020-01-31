package fz.idle.chat.serverImpl;

import fz.idle.chat.ConsumerApp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ChatConsumerHandler extends ChannelInboundHandlerAdapter {

    //调用登录接口
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String msage = String.valueOf(msg);
        if (msage.startsWith("user:")) {
            FlowChartGeneral.UserInfo = msage.replace("user:", "");
            System.out.println(FlowChartGeneral.UserInfo);
        } else {
            System.out.println(msg);
        }
    }

    //登录成功
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("客户端" + ctx.channel().remoteAddress() + "登录成功");
        ConsumerApp.ctx = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ctx.channel().closeFuture();
    }
}
