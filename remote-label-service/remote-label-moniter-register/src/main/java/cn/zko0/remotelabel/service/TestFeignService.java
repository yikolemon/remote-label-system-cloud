package cn.zko0.remotelabel.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
//Feign去调用
@FeignClient(value = "remote-label-user")
public interface TestFeignService {
    @GetMapping("")
    public String getOtherPort();

}