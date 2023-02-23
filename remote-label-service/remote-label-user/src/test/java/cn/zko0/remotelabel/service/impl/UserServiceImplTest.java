package cn.zko0.remotelabel.service.impl;

import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.mapper.UserMapper;
import cn.zko0.remotelabel.service.UserService;
import cn.zko0.remotelabel.util.AuthUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author duanfuqiang
 * @date 2023/2/23 15:46
 * @description
 */
@RunWith(SpringRunner.class)//必须
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void emailRegister() {
        User user = new User();
        user.setEmail("1142496307@qq.com");
        user.setPassword(AuthUtil.encryption("123456"));
        userMapper.insert(user);
    }
}