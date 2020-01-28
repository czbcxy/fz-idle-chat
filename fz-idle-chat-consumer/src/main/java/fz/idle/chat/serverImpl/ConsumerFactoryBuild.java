package fz.idle.chat.serverImpl;

import fz.idle.chat.core.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConsumerFactoryBuild implements ApplicationContextAware {
    private static final Map<String, FlowChartGeneral> beansOfType = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beansOfType.putAll(applicationContext.getBeansOfType(FlowChartGeneral.class));
    }

    public static FlowChartGeneral getFlowChart() {
        String simpleName = FlowChartGeneral.class.getSimpleName();
        return beansOfType.get(StringUtils.changeFirstCharacterCase(simpleName, false));
    }


}
