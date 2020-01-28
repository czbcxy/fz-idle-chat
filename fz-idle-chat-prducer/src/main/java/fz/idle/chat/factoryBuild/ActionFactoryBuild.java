package fz.idle.chat.factoryBuild;

import fz.idle.chat.core.StringUtils;
import fz.idle.chat.msg.service.ClientService;
import fz.idle.chat.msg.service.MessageService;
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
    private static final Map<String, MessageService> beansOfTypeMessage = new HashMap<>();
    private static final Map<String, FlowChartGeneral> beansOfTypeFlow = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beansOfType.putAll(applicationContext.getBeansOfType(ClientService.class));
        beansOfTypeMessage.putAll(applicationContext.getBeansOfType(MessageService.class));
        beansOfTypeFlow.putAll(applicationContext.getBeansOfType(FlowChartGeneral.class));
    }

    public static ClientService getClient(String task) {
        if (Objects.isNull(task)) {
            return null;
        }
        ClientService center = beansOfType.get(StringUtils.changeFirstCharacterCase(String.format(task + "%s", "Impl"),false));
        return Objects.isNull(center) ? null : center;
    }

    public static MessageService getMessage(String task) {
        if (Objects.isNull(task)) {
            return null;
        }
        MessageService center = beansOfTypeMessage.get(StringUtils.changeFirstCharacterCase(String.format(task + "%s", "Impl"),false));
        return Objects.isNull(center) ? null : center;
    }

    public static FlowChartGeneral getFlowChart() {
        String simpleName = FlowChartGeneral.class.getSimpleName();
        return beansOfTypeFlow.get(StringUtils.changeFirstCharacterCase(simpleName, false));
    }

}
