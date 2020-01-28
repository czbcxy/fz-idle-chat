package fz.idle.chat.serverImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ArrayBlockingQueue;


public class ChatConsumerHandler extends ChannelInboundHandlerAdapter  {

    public static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10000);

    //调用登录接口
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        queue.offer(msg.toString());
    }

    //登录成功
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        ConsumerFactoryBuild.getFlowChart().doChat(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
