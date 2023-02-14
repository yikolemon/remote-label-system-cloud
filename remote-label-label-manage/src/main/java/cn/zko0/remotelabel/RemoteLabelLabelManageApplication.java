package cn.zko0.remotelabel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RemoteLabelLabelManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteLabelLabelManageApplication.class, args);
    }

}
