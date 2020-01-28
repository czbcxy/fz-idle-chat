package fz.idle.chat.servceImpl;

import fz.idle.chat.factoryBuild.ActionFactoryBuild;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.msg.service.ClientService;
import fz.idle.chat.msg.util.ResponseResult;
import fz.idle.chat.msg.vo.ClientAllVo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatProviderHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ClientService user = ActionFactoryBuild.getInstance(ClientService.class.getSimpleName());
        LogParam param = new LogParam();
        ResponseResult<ClientAllVo> login = user.login(param);
        System.out.println("服务端接口到客户端的消息" + ctx.name());
        String execut = "successful";
        ctx.write(execut);
        ctx.flush();
        //登录成功
        if (true) {
//           查询
            //查询成功
            String query = "query successful";
            ctx.write(execut);
            ctx.flush();
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
