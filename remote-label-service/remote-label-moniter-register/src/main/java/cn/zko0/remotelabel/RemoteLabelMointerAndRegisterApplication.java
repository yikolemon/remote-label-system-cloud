package cn.zko0.remotelabel;

import cn.zko0.remotelabel.netty.MqttServerThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients //激活feign
public class RemoteLabelMointerAndRegisterApplication implements CommandLineRunner {

    @Autowired
    private MqttServerThread mqttServerThread;

    @Value("${mqtt.server.status}")
    private String mqttStatus;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RemoteLabelMointerAndRegisterApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        //on会被@Value自动转化为true
        if ("true".equals(mqttStatus)){
            mqttServerThread.start();
        }
    }

}
