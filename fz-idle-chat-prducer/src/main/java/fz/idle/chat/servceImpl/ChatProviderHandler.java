package fz.idle.chat.servceImpl;

import fz.idle.chat.entry.MessageDetail;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatProviderHandler extends ChannelInboundHandlerAdapter{


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageDetail detail = (MessageDetail)msg;
        //接收到客户端传过来的消息，
        //进行存储，发送给客户发送成功，同将消息推送给给另一个客户端。
//        String execut = getInstance("send").execut(detail);
        System.out.println("服务端接口到客户端的消息"+ctx.name());
        String execut = "successful";
        ctx.write(execut);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }



}
