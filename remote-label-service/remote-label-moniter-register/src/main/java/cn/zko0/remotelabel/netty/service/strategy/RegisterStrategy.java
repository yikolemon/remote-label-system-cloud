package cn.zko0.remotelabel.netty.service.strategy;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author duanfuqiang
 * @date 2023/3/7 23:40
 * @description
 */
@Component
public class RegisterStrategy implements TopicStrategy{

    @Autowired
    private LabelFeign labelFeign;

    @Override
    public void handle(Channel channel, MqttMessage mqttMessage) {
        PublishRequest publishRequest = MqttUtils.genObjByPublishMessage(mqttMessage);
        //异步注册
        channel.eventLoop().execute(() -> {
            List<Label> labelList = publishRequest.getLabelList();
            if (labelList!=null){
                labelFeign.insertLabelList(labelList);
                PublishResponse publishResponse = new PublishResponse(TerminalResponseCode.REGISTER_SUC);
                MqttMsgBack.publish(channel,"registAck", JSON.toJSONString(publishResponse));
            }
        });
    }
}
