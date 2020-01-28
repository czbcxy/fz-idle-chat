package fz.idle.chat.servceImpl;

import fz.idle.chat.Do.UserInfoDo;
import fz.idle.chat.Message.UserInfo;
import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.factoryBuild.ActionFactoryBuild;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatProviderHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageDetail detail = (MessageDetail) msg;
        //接收到客户端传过来的消息，
        //进行存储，发送给客户发送成功，同将消息推送给给另一个客户端。
//        String execut = getInstance("send").execut(detail);

        UserInfo user = ActionFactoryBuild.getInstance(UserInfo.class.getSimpleName());
        UserInfoDo userInfo = user.findUserInfo("123");
        System.out.println("服务端接口到客户端的消息" + ctx.name());
        String execut = "successful";
        ctx.write(execut);
        ctx.flush();
        //登录成功
        if (false) {
//           查询
            //查询成功
            if (false) {
                //循环聊天
                while (true) {
                    System.out.println("服务端接口到客户端的消息" + ctx.name());
                    ctx.write(execut);
                    ctx.flush();
                }
            }
        }
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


}
