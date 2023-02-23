package cn.zko0.remotelabel;

import cn.zko0.remotelabel.netty.BootNettyMqttServerThread;
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

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RemoteLabelMointerAndRegisterApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 启动  1883
        int port = 1883;
        BootNettyMqttServerThread bootNettyMqttServerThread = new BootNettyMqttServerThread(port);
        bootNettyMqttServerThread.start();
    }

}
