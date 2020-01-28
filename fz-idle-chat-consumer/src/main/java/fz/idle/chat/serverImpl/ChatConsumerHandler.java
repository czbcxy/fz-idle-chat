package fz.idle.chat.serverImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ChatConsumerHandler extends ChannelInboundHandlerAdapter  {

    private Object response;

    //调用登录接口
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.response = msg;
    }

    //登录成功
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        ConsumerFactoryBuild.getFlowChart().chat();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    public Object getResponse() {
        return response;
    }
}
