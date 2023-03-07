package cn.zko0.remotelabel.netty.service.strategy;

import cn.zko0.remotelabel.vo.PublishRequest;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;

/**
 * @author duanfuqiang
 * @date 2023/3/7 23:16
 * @description
 */
public interface TopicStrategy {
    public void handle(Channel channel, MqttMessage mqttMessage);

}
