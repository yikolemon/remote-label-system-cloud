package cn.zko0.remotelabel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.zko0.remotelabel.mapper")
public class RemoteLabelUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteLabelUserApplication.class, args);
    }

}
