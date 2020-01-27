package fz.idle.chat.serverImpl;

import fz.idle.chat.entry.MessageDetail;
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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class proxyInstance implements InvocationHandler {
    private Class<?> clazz;

    public proxyInstance(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(clazz, args);
        }
        return rpcInvoker(proxy, method, args);
    }

    private Object rpcInvoker(Object proxy, Method method, Object[] args) {
        MessageDetail metaData = new MessageDetail();
        metaData.setClientId("123");
        metaData.setDetail("message");
        metaData.setFriendId("456");
        RpcConsumerHandler handler = new RpcConsumerHandler();
        transmit(metaData, handler);
        return handler.getResponse();
    }

    private void transmit(MessageDetail metaData, final RpcConsumerHandler handler) {
        EventLoopGroup workGroup = null;
        try {
            workGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pip = socketChannel.pipeline();
                    pip.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pip.addLast(new LengthFieldPrepender(4));
                    pip.addLast("encoder", new ObjectEncoder());
                    pip.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                    pip.addLast(handler);
                }
            });
            String[] split = getHost();
            ChannelFuture future = bootstrap.connect(split[0], Integer.parseInt(split[1])).sync();
            future.channel().writeAndFlush(metaData).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("consumer error {}", e.getCause().getMessage());
            e.printStackTrace();
        } finally {
            if (workGroup != null) {
                workGroup.shutdownGracefully();
            }
        }
    }

    private String[] getHost() {
        return new String[]{"127.0.0.1","8080"};
    }
}
