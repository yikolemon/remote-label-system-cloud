package cn.zko0.remotelabel.service;

import cn.dev33.satoken.util.SaResult;
import cn.zko0.remotelabel.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zko0
 * @since 2023-02-15
 */
public interface UserService extends IService<User> {

    public SaResult doLogin(User user);

    public SaResult emailRegister(User user);

    public String sendEmailCode(String email);


}
