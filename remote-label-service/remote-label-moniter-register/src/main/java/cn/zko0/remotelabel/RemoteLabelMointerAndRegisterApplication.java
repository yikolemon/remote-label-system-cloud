package cn.zko0.remotelabel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients //激活feign
public class RemoteLabelMointerAndRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteLabelMointerAndRegisterApplication.class, args);
    }

}
