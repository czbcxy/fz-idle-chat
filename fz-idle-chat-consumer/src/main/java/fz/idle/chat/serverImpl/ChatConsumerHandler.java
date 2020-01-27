package fz.idle.chat.serverImpl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatConsumerHandler extends ChannelInboundHandlerAdapter {

    private Object response;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.response = msg;
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        for (int i = 0 ; i < 10 ; i++) {
            ////开始聊天业务编写。
            final ByteBuf byteBuf = ctx.alloc().buffer(8);
            byteBuf.writeBytes("你好，欢迎建立长连接".getBytes());
            ctx.writeAndFlush(byteBuf, ctx.channel().newPromise());
            System.out.println("TimeServerHandler，有新连接");
        }
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
