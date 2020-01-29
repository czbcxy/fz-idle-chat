package fz.idle.chat.serverImpl;

import fz.idle.chat.entry.MetaData;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FlowChartGeneral {
    private ChannelHandlerContext ctx;

    public void setCtx(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }

    //循环聊天
    public void doChat(MetaData metaData) {
        ctx.writeAndFlush(metaData);
    }
}
