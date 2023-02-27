package cn.zko0.remotelabel.feign;

import cn.zko0.remotelabel.LabelApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:33
 * @description
 */

@FeignClient(value = "remote-label-manage",contextId = "labelApi")
public interface LabelFeign extends LabelApi {
}
