package cn.zko0.remotelabel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.mapper.UserMapper;
import cn.zko0.remotelabel.service.UserService;
import cn.zko0.remotelabel.util.AuthUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zko0
 * @since 2023-02-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SaResult doLogin(String username,String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User user = userMapper.selectOne(wrapper);
        if (user==null){
            return SaResult.error("用户不存在");
        }else {
            String dbPassword = user.getPassword();
            if (AuthUtil.checkPassword(password,dbPassword)){
                StpUtil.login(user.getUserId());
                return SaResult.ok("登录成功");
            }else {
                return SaResult.error("密码错误");
            }
        }
    }

}
