package fz.idle.chat.servceImpl;

import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.factorybuild.ControlCenter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChatProviderHandler extends ChannelInboundHandlerAdapter implements ApplicationContextAware {
    private static final Map<String, ControlCenter> beansOfType = new HashMap<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageDetail detail = (MessageDetail)msg;
        //接收到客户端传过来的消息，
        //进行存储，发送给客户发送成功，同将消息推送给给另一个客户端。
//        String execut = getInstance("send").execut(detail);
        String execut = "successful";
        ctx.write(execut);
        ctx.flush();
        ctx.close();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beansOfType.putAll(applicationContext.getBeansOfType(ControlCenter.class));
    }

    public ControlCenter getInstance(String task) {
        if (Objects.isNull(task)) {
            return null;
        }
        ControlCenter center = beansOfType.get(task.concat(ControlCenter.class.getSimpleName()));
        return Objects.isNull(center) ? null : center;
    }
}
