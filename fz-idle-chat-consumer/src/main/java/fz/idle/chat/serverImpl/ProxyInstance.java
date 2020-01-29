package fz.idle.chat.serverImpl;

import fz.idle.chat.core.AbstractApplicationContent;
import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.entry.MetaData;
import fz.idle.chat.param.LogParam;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class ProxyInstance extends AbstractApplicationContent {

    @Autowired
    ChatConsumerHandler chatConsumerHandler;

    //开始登录聊天
    public void login(MetaData metaData) {
        try {
            EventLoopGroup workGroup = new NioEventLoopGroup();
            ChannelFuture future = transmit(workGroup);
            assert future != null;
            future.channel().writeAndFlush(metaData).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("consumer error {}", e.getCause().getMessage());
            e.printStackTrace();
        }
    }

    private ChannelFuture transmit(EventLoopGroup workGroup) {
        ChannelFuture future = null;
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pip = socketChannel.pipeline();
                    pip.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pip.addLast(new LengthFieldPrepender(4));
                    pip.addLast("encoder", new ObjectEncoder());
                    pip.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                    pip.addLast(chatConsumerHandler);
                }
            });
            future = bootstrap.connect(host, port).sync();
        } catch (Exception e) {
            log.error("consumer error {}", e.getCause().getMessage());
            e.printStackTrace();
        }
        return future;
    }

}
