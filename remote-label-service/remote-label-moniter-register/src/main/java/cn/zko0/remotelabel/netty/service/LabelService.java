package cn.zko0.remotelabel.netty.service;

import cn.zko0.remotelabel.config.MQConfig;
import cn.zko0.remotelabel.entity.Label;
import cn.zko0.remotelabel.enumerate.TerminalResponseCode;
import cn.zko0.remotelabel.feign.LabelFeign;
import cn.zko0.remotelabel.netty.MqttMsgBack;
import cn.zko0.remotelabel.util.MqttUtils;
import cn.zko0.remotelabel.vo.PublishRequest;
import cn.zko0.remotelabel.vo.PublishResponse;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duanfuqiang
 * @date 2023/2/27 10:14
 * @description
 */

@Component
public class LabelService {

    @Autowired
    private LabelFeign labelFeign;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //处理publish消息,返回处理后的结果消息
    public void publishHandler(Channel channel,MqttMessage mqttMessage){

        //debug测试回发
        PublishResponse publishResponse = MqttUtils.genObjByPublishMessage(mqttMessage);
        channel.eventLoop().execute(() -> {
            if (publishResponse.isRegisterMsg()){
                //注册消息
                List<Label> labelList = publishResponse.getLabelList();
                if (labelList!=null){
                    labelFeign.insertLabelList(labelList);
                    PublishRequest publishRequest = new PublishRequest(TerminalResponseCode.REGISTER_SUC);
                    MqttMsgBack.publish(channel,"registAck", JSON.toJSONString(publishRequest));
                }
            }else if (publishResponse.isMonitorMsg()){
                String msg = MqttUtils.genContentByPublishMessage(mqttMessage);
                //监控消息
                rabbitTemplate.convertAndSend(MQConfig.LABEL_REPORT_EXCHANGE,MQConfig.routingKey,msg);
            }
        });
    }

}
