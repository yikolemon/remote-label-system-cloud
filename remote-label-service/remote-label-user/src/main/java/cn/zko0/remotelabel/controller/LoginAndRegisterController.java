package cn.zko0.remotelabel.controller;

import cn.dev33.satoken.util.SaResult;
import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanfuqiang
 * @date 2023/2/15 22:10
 * @description
 */
@RestController
@RequestMapping("/user")
public class LoginAndRegisterController {

    @Autowired
    private UserService userService;

    // 会话登录接口
    @RequestMapping("/doLogin")
    public SaResult doLogin(@RequestBody User user) {
        return userService.doLogin(user);
    }

    // 会话注册
    @RequestMapping("/doRegister")
    public SaResult doRegister(@RequestBody User user) {
        if (user.getEmail()!=null){
            userService.emailRegister(user);
            return SaResult.ok("注册成功");
        }else {
            return SaResult.error("邮箱为空");
        }
    }

    @RequestMapping("/sendEmailCode")
    public SaResult sendEmailCode(@RequestBody User user){
        if (user.getEmail()!=null){
            userService.sendEmailCode(user.getEmail());
            return SaResult.ok("验证码邮件已发送");
        }else{
            return SaResult.error("邮箱为空");
        }
    }

    @RequestMapping("/resetPassword")
    public SaResult resetPassword(@RequestBody User user){
        return null;
    }

}
