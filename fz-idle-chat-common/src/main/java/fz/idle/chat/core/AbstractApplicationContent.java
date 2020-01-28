package fz.idle.chat.core;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public abstract class AbstractApplicationContent {
    private static final Object lock = new Object();
    public static String host;
    public static int port;
    private static final String application = "Application.yml";
    protected static final Properties config = new Properties();

    @PostConstruct
    public void Init() {
        synchronized (lock) {
            doLoadProperties();
        }
    }

    public void doLoadProperties() {
        InputStream io = null;
        try {
            io = this.getClass().getClassLoader().getResourceAsStream(application);
            config.load(io);
            String hostStr = config.getProperty("netty.server.host");
            String portStr = config.getProperty("netty.server.port");
            host = Objects.isNull(hostStr) ? null : hostStr;
            port = Objects.isNull(portStr) ? 0 : Integer.parseInt(portStr);
        } catch (Exception e) {
            log.error("cofig init error : {}", e.getCause().getMessage());
        } finally {
            try {
                if (io != null) {
                    io.close();
                }
            } catch (IOException e) {
                log.error("{}", e);
            }
        }
    }
}