package cn.zko0.remotelabel.netty.service;

import cn.zko0.remotelabel.entity.Label;
import cn.zko0.remotelabel.enumerate.TerminalResponseCode;
import cn.zko0.remotelabel.feign.LabelFeign;
import cn.zko0.remotelabel.netty.MqttMsgBack;
import cn.zko0.remotelabel.netty.service.strategy.TopicContext;
import cn.zko0.remotelabel.netty.service.strategy.TopicStrategy;
import cn.zko0.remotelabel.netty.service.strategy.TopicStrategyFactory;
import cn.zko0.remotelabel.provider.MoniterMsgProvider;
import cn.zko0.remotelabel.util.MqttUtils;
import cn.zko0.remotelabel.vo.PublishResponse;
import cn.zko0.remotelabel.vo.PublishRequest;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;
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
    private MoniterMsgProvider moniterMsgProvider;

    //处理publish消息,返回处理后的结果消息
    public void publishHandler(Channel channel,MqttMessage mqttMessage){
        MqttPublishVariableHeader variableHeader = (MqttPublishVariableHeader)mqttMessage.variableHeader();
        String topicName = variableHeader.topicName();
        //通过topicName区分消息类型
        TopicContext topicContext = new TopicContext(TopicStrategyFactory.getTopicStrategy(topicName));
        //topic策略处理
        topicContext.executeStrategy(channel,mqttMessage);
    }

}
