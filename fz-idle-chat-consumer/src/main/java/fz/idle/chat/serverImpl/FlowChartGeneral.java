package fz.idle.chat.serverImpl;

import fz.idle.chat.entry.MetaData;
import fz.idle.chat.enums.Types;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

@Component
public class FlowChartGeneral {
    //循环聊天
    public void doChat(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
            MetaData metaData = new MetaData();
            metaData.setType(Types.chat.name());
            metaData.setDate("来子客户端的消息");
            ctx.write(metaData);
            ctx.flush();
        }
    }
}
