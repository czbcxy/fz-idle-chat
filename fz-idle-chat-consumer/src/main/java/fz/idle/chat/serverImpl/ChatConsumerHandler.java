package fz.idle.chat.serverImpl;

import fz.idle.chat.Do.UserInfoDo;
import fz.idle.chat.Message.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatConsumerHandler extends ChannelInboundHandlerAdapter  {

    private UserInfoDo response;

    //调用登录接口
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.response = (UserInfoDo) msg;
    }

    //登录成功
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        ActionFactoryBuild.getFlowChart().chat();
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
