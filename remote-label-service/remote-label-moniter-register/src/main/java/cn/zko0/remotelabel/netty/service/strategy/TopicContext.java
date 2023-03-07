package cn.zko0.remotelabel.netty.service.strategy;

import cn.zko0.remotelabel.vo.PublishRequest;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author duanfuqiang
 * @date 2023/3/7 23:16
 * @description
 */
public class TopicContext {

    private TopicStrategy topicStrategy;


    public TopicContext(TopicStrategy topicStrategy) {
        this.topicStrategy = topicStrategy;
    }

    //context通用处理
    public void executeStrategy(Channel channel, MqttMessage mqttMessage){
        if (topicStrategy==null){
            throw new RuntimeException("该Topic策略未实现");
        }
        topicStrategy.handle(channel,mqttMessage);
    }

}
