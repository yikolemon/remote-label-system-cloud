package cn.zko0.remotelabel.controller;

import cn.dev33.satoken.util.SaResult;
import cn.zko0.remotelabel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanfuqiang
 * @date 2023/2/15 22:10
 * @description
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    // 会话登录接口
    @RequestMapping("/doLogin")
    public SaResult doLogin(String name, String pwd) {
        return userService.doLogin(name, pwd);
    }



}
