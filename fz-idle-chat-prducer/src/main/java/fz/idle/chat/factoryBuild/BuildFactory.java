package fz.idle.chat.factoryBuild;

import fz.idle.chat.msg.service.ClientService;
import fz.idle.chat.msg.service.MessageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class BuildFactory implements InitializingBean {

    public static final Map<String, Object> objeInstance = new ConcurrentHashMap<>();

    @Autowired
    private ClientService clientService;
    @Autowired
    private MessageService messageService;

    @Override
    public void afterPropertiesSet() throws Exception {
        objeInstance.put(ClientService.class.getSimpleName(),clientService);
        objeInstance.put(MessageService.class.getSimpleName(),messageService);
    }

    public static ClientService getClientService(){
        return (ClientService)objeInstance.get(ClientService.class.getSimpleName());
    }
    public static MessageService getMessageService(){
        return (MessageService)objeInstance.get(MessageService.class.getSimpleName());
    }
}
