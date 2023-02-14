package cn.zko0.remotelabel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RemoteLabelGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteLabelGetwayApplication.class, args);
    }

}
