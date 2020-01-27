package fz.idle.chat.servceImpl;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public abstract class AbstractApplicationContent {
    private static final Object lock = new Object();
    public static int port;
    private static final String application = "Application.properties";
    protected static final Properties config = new Properties();

    public void providerInit() {
        synchronized (lock) {
            doLoadProperties();
        }
    }

    public void doLoadProperties() {
        InputStream io = null;
        try {
            io = this.getClass().getClassLoader().getResourceAsStream(application);
            config.load(io);
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