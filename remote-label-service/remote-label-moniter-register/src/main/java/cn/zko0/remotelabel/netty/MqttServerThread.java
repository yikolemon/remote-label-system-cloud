package cn.zko0.remotelabel.netty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  蚂蚁舞
 */
@Component
public class MqttServerThread extends Thread {

    @Value("${mqtt.port}")
    private int port;

    @Autowired
    private MqttServer mqttServer;


    public void run() {
        mqttServer.startup(port);
    }


}