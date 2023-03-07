package cn.zko0.remotelabel.netty.service.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duanfuqiang
 * @date 2023/3/7 23:30
 * @description
 */
public class TopicStrategyFactory {
    public static final Map<String,TopicStrategy> strategyMap=new HashMap<>();

    static {
        strategyMap.put("registerMsg",new RegisterStrategy());
        strategyMap.put("reportMsg",new ReportStrategy());
    }

    public static TopicStrategy getTopicStrategy(String topic){
        return strategyMap.getOrDefault(topic,null);
    }
}
