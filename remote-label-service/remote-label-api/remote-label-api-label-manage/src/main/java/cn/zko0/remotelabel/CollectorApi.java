package cn.zko0.remotelabel;

import cn.zko0.remotelabel.entity.Collector;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author duanfuqiang
 * @date 2023/2/27 10:44
 * @description
 */

@RequestMapping("/collector")
public interface CollectorApi {

    @RequestMapping("/getExist")
    public boolean getExist(@RequestBody String collectorId);


}
