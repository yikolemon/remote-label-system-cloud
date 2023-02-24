package cn.zko0.remotelabel.netty;



import cn.zko0.remotelabel.netty.service.MessageProcessService;
import io.netty.channel.*;
import io.netty.handler.codec.mqtt.*;
import java.io.IOException;

import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 蚂蚁舞
 */
@ChannelHandler.Sharable
@Slf4j
@Component
public class MqttChannelInboundHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private MessageProcessService messageProcessService;




    /**
     * 	从客户端收到新的数据时，这个方法会在收到消息时被调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception, IOException {
    	if (null != msg) {
            MqttMessage mqttMessage = (MqttMessage) msg;
            log.info("info--"+mqttMessage.toString());
            MqttFixedHeader mqttFixedHeader = mqttMessage.fixedHeader();
            Channel channel = ctx.channel();
 
            if(mqttFixedHeader.messageType().equals(MqttMessageType.CONNECT)) {
                //创建closeFuture,处理下线操作
                //登录验证
                if (messageProcessService.login(channel,mqttMessage)) {
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
                        messageProcessService.offLine(clientId);
                    }
                });
            }
//            }else {
//                //checkLogin
//                if (!messageProcessService.checkOnline(mqttMessage)) {
//                    //没有登录，返回错误
//                    MqttMsgBack.notAuthorized(channel,mqttMessage);
//                }
//            }
            switch (mqttFixedHeader.messageType()){
                case PUBLISH:		//	客户端发布消息
                	//	PUBACK报文是对QoS 1等级的PUBLISH报文的响应
                    messageProcessService.handlePublish(mqttMessage);
                	MqttMsgBack.puback(channel, mqttMessage);
                    break;
                case PUBREL:		//	发布释放
                	//	PUBREL报文是对PUBREC报文的响应
                	//	to do
                	MqttMsgBack.pubcomp(channel, mqttMessage);
                    break;
//这里的mqtt只是用到了client的publish其他的省略
//                case SUBSCRIBE:		//	客户端订阅主题
//                	//	客户端向服务端发送SUBSCRIBE报文用于创建一个或多个订阅，每个订阅注册客户端关心的一个或多个主题。
//                	//	为了将应用消息转发给与那些订阅匹配的主题，服务端发送PUBLISH报文给客户端。
//                	//	SUBSCRIBE报文也（为每个订阅）指定了最大的QoS等级，服务端根据这个发送应用消息给客户端
//                	// 	to do
//                	MqttMsgBack.suback(channel, mqttMessage);
//                    break;
//                case UNSUBSCRIBE:	//	客户端取消订阅
//                	//	客户端发送UNSUBSCRIBE报文给服务端，用于取消订阅主题
//                	//	to do
//                	MqttMsgBack.unsuback(channel, mqttMessage);
//                    break;
                case PINGREQ:		//	客户端发起心跳
                	//	客户端发送PINGREQ报文给服务端的
                	//	在没有任何其它控制报文从客户端发给服务的时，告知服务端客户端还活着
                	//	请求服务端发送 响应确认它还活着，使用网络以确认网络连接没有断开
                    messageProcessService.extendOlinePermession(channel,mqttMessage);
                	MqttMsgBack.pingresp(channel, mqttMessage);
                    break;
                case DISCONNECT:	//	客户端主动断开连接
                	//	DISCONNECT报文是客户端发给服务端的最后一个控制报文， 服务端必须验证所有的保留位都被设置为0
                	//	to do
                    channel.close(); //触发closeFuture
                    break;
                default:
                    break;
            }
    	}
    }
 
    /**
     * 	从客户端收到新的数据、读取完成时调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws IOException {
    }
 
    /**
     * 	客户端与服务端第一次建立连接时执行 在channelActive方法之前执行
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }
 
    /**
     * 	客户端与服务端 断连时执行 channelInactive方法之后执行
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }
 
    /**
     * 	当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);  
        ctx.close();
    }
 
    /**
     * 	客户端与服务端第一次建立连接时执行
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
 
    /**
     * 	客户端与服务端 断连时执行
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException {
        super.channelInactive(ctx);
    }
    
    /**
     * 	服务端 当读超时时 会调用这个方法
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception, IOException {
        super.userEventTriggered(ctx, evt);
        ctx.close();
    }
 
    
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }
 
}