package cn.zko0.remotelabel.netty.service;

import cn.zko0.remotelabel.netty.dao.CollectorDao;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author duanfuqiang
 * @date 2023/2/24 13:35
 * @description
 */

@Component
public class MessageProcessService {

    @Value("${mqtt.userName}")
    private String userName;

    //mqtt服务器用户名
    @Value("${mqtt.password}")
    private String password;

    @Autowired
    private CollectorDao collectorDao;

    //登录认证
    public  boolean login(Channel channel, MqttMessage mqttMessage){
        try {
            MqttConnectPayload payload = (MqttConnectPayload) mqttMessage.payload();
            if ((userName.equals(payload.userName()))&&(password.equals(payload.password()))){
                //密码验证通过
                //给20s在线权限
                String clientId = payload.clientIdentifier();
                collectorDao.onlineLimitTime(clientId);
                //向channel中放入clientId，在channel关闭时执行offlien等操作
                channel.attr(AttributeKey.newInstance("clientId")).set(clientId);
                return true;
            }else {
                //验证失败
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkOnline(MqttMessage mqttMessage){
        MqttConnectPayload payload = (MqttConnectPayload) mqttMessage.payload();
        String clientId = payload.clientIdentifier();
        return collectorDao.checkOnline(clientId);
    }

    public void handlePublish(MqttMessage mqttMessage){

    }

    public String genContentByPublishMessage(MqttMessage mqttMessage){
        ByteBuf content = (ByteBuf) mqttMessage.payload();
        byte[] arr=new byte[content.capacity()];
        content.readBytes(arr);
        return new String(arr);
    }

    public void extendOlinePermession(Channel channel,MqttMessage mqttMessage){
        String clientId = (String)channel.attr(AttributeKey.valueOf("clientId")).get();
        collectorDao.extendOnlinePermission(clientId);
    }


    public void offLine(String clientId){
        collectorDao.offLine(clientId);
    }
}
