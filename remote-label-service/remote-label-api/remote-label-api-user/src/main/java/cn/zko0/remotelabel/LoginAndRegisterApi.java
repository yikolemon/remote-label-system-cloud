package cn.zko0.remotelabel;

import cn.zko0.remotelabel.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author duanfuqiang
 * @date 2023/2/23 14:10
 * @description
 */
public interface LoginAndRegisterApi {

    @GetMapping("/auth/getRoleById")
    public String getRoleById(String id);

}
