package cn.zko0.remotelabel.feign;
import cn.zko0.remotelabel.*;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author duanfuqiang
 * @date 2023/2/23 14:36
 * @description
 */
@FeignClient("remote-label-user")
public interface LoginAndRegisterFeign extends LoginAndRegisterApi{

}
