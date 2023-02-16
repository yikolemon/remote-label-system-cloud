package cn.zko0.remotelabel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.mapper.UserMapper;
import cn.zko0.remotelabel.service.UserService;
import cn.zko0.remotelabel.util.AuthUtil;
import cn.zko0.remotelabel.util.CodeUtil;
import cn.zko0.remotelabel.util.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public SaResult doLogin(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        User dbUser = userMapper.selectOne(wrapper);
        if (user==null){
            return SaResult.error("用户不存在");
        }else {
            if (AuthUtil.checkPassword(user.getPassword(),dbUser.getPassword())){
                StpUtil.login(dbUser.getUserId());
                return SaResult.ok("登录成功");
            }else {
                return SaResult.error("密码错误");
            }
        }
    }

    @Override
    public String sendEmailCode(String email) {
        //发送验证码

        String code = CodeUtil.getCode4();
        //保存到redis，设置三分钟超时
        redisUtil.setCacheObject("checkCode_"+email, code,10, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public SaResult emailRegister(User user) {
        String redisCode = (String)(redisUtil.getCacheObject("checkCode_" + user.getEmail()));
        String code=user.getCode();
        if (StringUtils.isEmpty(user.getEmail())){
            return SaResult.error("密码不能为空");
        }
        if (redisCode.equals(code)){
            userMapper.insert(user);
            return SaResult.ok("注册成功");
        }else {
            return SaResult.error("验证码错误");
        }
    }

}
