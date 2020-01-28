package fz.idle.chat.factoryBuild;

import fz.idle.chat.msg.service.ClientService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ActionFactoryBuild implements ApplicationContextAware {
    private static final Map<String, ClientService> beansOfType = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beansOfType.putAll(applicationContext.getBeansOfType(ClientService.class));
    }

    public static ClientService getInstance(String task) {
        if (Objects.isNull(task)) {
            return null;
        }
        ClientService center = beansOfType.get(String.format(task + "%s", "Impl"));
        return Objects.isNull(center) ? null : center;
    }

}
