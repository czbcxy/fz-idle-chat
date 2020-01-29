package fz.idle.chat.serverImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatConsumerHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private FutureResponse futureResponse;

    @Autowired
    private  FlowChartGeneral flowChartGeneral;

    //调用登录接口
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        futureResponse.setDate(msg);
    }

    //登录成功
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        flowChartGeneral.setCtx(ctx);
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
