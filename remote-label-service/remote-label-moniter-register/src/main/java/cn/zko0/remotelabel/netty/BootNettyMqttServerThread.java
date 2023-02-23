package cn.zko0.remotelabel.netty;
 
 
/**
 *  蚂蚁舞
 */
public class BootNettyMqttServerThread extends Thread {
 
    private final int port;
 
    public BootNettyMqttServerThread(int port){
        this.port = port;
    }
 
    public void run() {
        BootNettyMqttServer iotUdpBootstrap = new BootNettyMqttServer();
        iotUdpBootstrap.startup(this.port);
    }
 
 
}