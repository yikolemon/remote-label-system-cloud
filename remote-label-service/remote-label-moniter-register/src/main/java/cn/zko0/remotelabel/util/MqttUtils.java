package cn.zko0.remotelabel.util;

import cn.zko0.remotelabel.vo.PublishResponse;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.mqtt.MqttMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:49
 * @description
 */
@Slf4j
public class MqttUtils {
    public static String genContentByPublishMessage(MqttMessage mqttMessage){
        ByteBuf content = (ByteBuf) mqttMessage.payload();
        byte[] arr=new byte[content.capacity()];
        content.readBytes(arr);
        return new String(arr);
    }

    public static PublishResponse genObjByPublishMessage(MqttMessage mqttMessage){
        try {
            ByteBuf content = (ByteBuf) mqttMessage.payload();
            byte[] arr=new byte[content.capacity()];
            content.readBytes(arr);
            String json = new String(arr);
            return PublishMsgSerializeUtils.json2Obj(json);
        } catch (Exception e) {
            //序列化失败，publish格式错误
            log.error("publish发送格式失败");
            e.printStackTrace();
            return null;
        }
    }
}
