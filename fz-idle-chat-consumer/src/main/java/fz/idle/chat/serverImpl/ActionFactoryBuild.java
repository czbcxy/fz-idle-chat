package fz.idle.chat.serverImpl;

import fz.idle.chat.Message.UserInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ActionFactoryBuild implements ApplicationContextAware {
    private static final Map<String, FlowChartGeneral> beansOfType = new HashMap<>();
    private static final Map<String, UserInfo> beansOfTypeUser = new HashMap<>();

    public static UserInfo getInstance(String task) {
        if (Objects.isNull(task)) {
            return null;
        }
        UserInfo center = beansOfTypeUser.get(String.format(task + "%s", "Impl"));
        return Objects.isNull(center) ? null : center;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beansOfType.putAll(applicationContext.getBeansOfType(FlowChartGeneral.class));
        beansOfTypeUser.putAll(applicationContext.getBeansOfType(UserInfo.class));
    }

    public static FlowChartGeneral getFlowChart(){
        return beansOfType.get(FlowChartGeneral.class.getSimpleName());
    }


}
