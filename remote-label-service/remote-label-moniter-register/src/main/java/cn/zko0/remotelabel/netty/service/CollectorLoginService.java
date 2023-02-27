package cn.zko0.remotelabel.netty.service;

import cn.zko0.remotelabel.feign.CollectorFeign;
import cn.zko0.remotelabel.netty.MqttMsgBack;
import cn.zko0.remotelabel.netty.dao.CollectorRedisDao;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttMessage;
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
public class CollectorLoginService {

    @Value("${mqtt.userName}")
    private String userName;

    //mqtt服务器用户名
    @Value("${mqtt.password}")
    private String password;

    @Autowired
    private CollectorRedisDao collectorRedisDao;

    @Autowired
    private CollectorFeign collectorFeign;

    public void connectHandler(Channel channel,MqttMessage mqttMessage){
        if (login(channel,mqttMessage)) {
            //验证成功
            //	在一个网络连接上，客户端只能发送一次CONNECT报文。服务端必须将客户端发送的第二个CONNECT报文当作协议违规处理并断开客户端的连接
            //	to do 建议connect消息单独处理，用来对客户端进行认证管理等 这里直接返回一个CONNACK消息
            MqttMsgBack.connack(channel, mqttMessage);
        } else {
            //conn nak认证失败，连接拒绝
            MqttMsgBack.connnak(channel, mqttMessage);
        }
        // 2. 异步处理，异步处理，上面真正执行关闭channel的EventLoop线程 成功关闭channel后就会调用这里面的方法
        ChannelFuture channelFuture = channel.closeFuture();
        //channel关闭监听器，处理登出操作
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                String clientId =(String) channelFuture.channel().attr(AttributeKey.valueOf("clientId")).get();
                offLine(clientId);
            }
        });
    }


    //登录认证
    public  boolean login(Channel channel, MqttMessage mqttMessage){
        try {
            MqttConnectPayload payload = (MqttConnectPayload) mqttMessage.payload();
            if ((userName.equals(payload.userName()))&&(password.equals(payload.password()))){
                //密码验证通过
                //给20s在线权限
                String clientId = payload.clientIdentifier();
                //clientID未注册
                if (!collectorFeign.getExist(clientId)) {
                    return false;
                }
                collectorRedisDao.onlineLimitTime(clientId);
                //向channel中放入clientId，在channel关闭时执行offlien等操作
                channel.attr(AttributeKey.valueOf("clientId")).setIfAbsent(clientId);
                return true;
            }else {
                //验证失败
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }




    public void extendOlinePermession(Channel channel,MqttMessage mqttMessage){
        String clientId = (String)channel.attr(AttributeKey.valueOf("clientId")).get();
        collectorRedisDao.extendOnlinePermission(clientId);
    }


    public void offLine(String clientId){
        collectorRedisDao.offLine(clientId);
    }
}
