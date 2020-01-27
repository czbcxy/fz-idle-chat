package fz.idle.chat.serverImpl;

import fz.idle.chat.servceImpl.AbstractApplicationContent;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * Hello world!
 */
@Slf4j
public class RpcConsumer extends AbstractApplicationContent {


    public <T> T create(final Class<?> clazz) {
        providerInit();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new proxyInstance(clazz));
    }
}
