package fz.idle.chat.serverImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatConsumerHandler extends ChannelInboundHandlerAdapter {

    //调用登录接口
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
    }

    //登录成功
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        ConsumerFactoryBuild.getFlowChart().setCtx(ctx);
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
