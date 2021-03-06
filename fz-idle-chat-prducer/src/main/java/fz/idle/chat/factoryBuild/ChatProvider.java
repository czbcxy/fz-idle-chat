package fz.idle.chat.factoryBuild;

import fz.idle.chat.core.AbstractApplicationContent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 */
@Slf4j
@Component
public class ChatProvider extends AbstractApplicationContent {

    public void init() {
        Init();
        start();
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGourp = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workGourp).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pip = socketChannel.pipeline();
                pip.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pip.addLast(new LengthFieldPrepender(4));

                pip.addLast("encoder", new ObjectEncoder());
                pip.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                pip.addLast(new ChatProviderHandler());
            }
        }).option(ChannelOption.SO_BACKLOG, 128).option(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture future = server.bind(port).sync();
            log.info("Netty server start successful . port = {}", port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("Netty server start error : {}", e.getCause().getMessage());
        }

    }

}
