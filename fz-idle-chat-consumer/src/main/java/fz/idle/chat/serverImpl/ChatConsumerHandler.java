package fz.idle.chat.serverImpl;

import fz.idle.chat.Do.UserInfoDo;
import fz.idle.chat.Message.UserInfo;
import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.factorybuild.ActionFactoryBuild;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatConsumerHandler extends ChannelInboundHandlerAdapter {

    private UserInfoDo response;

    //调用登录接口
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        UserInfo user = ActionFactoryBuild.getInstance(UserInfo.class.getSimpleName());
        UserInfoDo userInfo = user.findUserInfo("");
        this.response = userInfo;
    }

    //登录成功
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        //判断是否登录成功，
        if (false) {
//            失败返回

        }
        //成功就循环进行对话
        while (true) {

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
