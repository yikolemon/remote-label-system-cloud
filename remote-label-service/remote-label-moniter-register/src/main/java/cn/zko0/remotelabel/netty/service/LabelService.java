package cn.zko0.remotelabel.netty.service;

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
 * @date 2023/2/27 10:14
 * @description
 */

@Component
public class LabelService {

    @Autowired
    private LabelFeign labelFeign;

    //处理publish消息,返回处理后的结果消息
    public MqttMessage publishHandler(Channel channel,MqttMessage mqttMessage){

        //debug测试回发
        PublishRequest publishRequest = new PublishRequest(TerminalResponseCode.REGISTER_SUC);
        MqttMsgBack.publish(channel,"registAck", JSON.toJSONString(publishRequest));
        PublishResponse publishResponse = MqttUtils.genObjByPublishMessage(mqttMessage);

        if (publishResponse.isRegisterMsg()){
            //注册消息
            List<Label> labelList = publishResponse.getLabelList();
            if (labelList!=null){
                labelFeign.insertLabelList(labelList);
            }
        }else if (publishResponse.isMonitorMsg()){
            //监控消息

        }
        //
        return null;

    }

    public void register(PublishResponse publishResponse){

    }

    public void moniter(PublishResponse publishResponse){

    }

}
