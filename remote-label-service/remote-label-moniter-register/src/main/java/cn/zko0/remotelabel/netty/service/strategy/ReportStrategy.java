package cn.zko0.remotelabel.netty.service.strategy;

import cn.zko0.remotelabel.provider.MoniterMsgProvider;
import cn.zko0.remotelabel.vo.PublishRequest;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author duanfuqiang
 * @date 2023/3/8 0:01
 * @description
 */
@Component
public class ReportStrategy implements TopicStrategy{

    @Autowired
    MoniterMsgProvider moniterMsgProvider;

    @Override
    public void handle(Channel channel, MqttMessage mqttMessage) {
        channel.eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                moniterMsgProvider.publishMoniterMsg(mqttMessage);
            }
        });
    }
}
