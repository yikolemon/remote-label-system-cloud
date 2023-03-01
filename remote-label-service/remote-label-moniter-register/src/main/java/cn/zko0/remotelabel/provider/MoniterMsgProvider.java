package cn.zko0.remotelabel.provider;

import cn.zko0.remotelabel.config.MQConfig;
import cn.zko0.remotelabel.util.MqttUtils;
import io.netty.handler.codec.mqtt.MqttMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author duanfuqiang
 * @date 2023/3/1 15:42
 * @description
 */
@Component
public class MoniterMsgProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishMoniterMsg(MqttMessage mqttMessage){
        String content = MqttUtils.genContentByPublishMessage(mqttMessage);
        rabbitTemplate.convertAndSend(MQConfig.LABEL_REPORT_EXCHANGE,MQConfig.routingKey,content);
    }

}
