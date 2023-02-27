package cn.zko0.remotelabel.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.zko0.remotelabel.CollectorApi;

@FeignClient(value = "remote-label-manage",contextId = "collectorApi")
public interface CollectorFeign extends CollectorApi{
}