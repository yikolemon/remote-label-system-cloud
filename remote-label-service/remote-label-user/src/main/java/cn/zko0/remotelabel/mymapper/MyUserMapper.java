package cn.zko0.remotelabel.mymapper;

import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author duanfuqiang
 * @date 2023/2/23 13:52
 * @description
 */
@Component("myMapper")
public class MyUserMapper {

    @Autowired
    private UserMapper userMapper;

    public User getUserByEmail(String email){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        return userMapper.selectOne(wrapper);
    }

    public void updatePwdByEmail(User user){
        String email = user.getEmail();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        userMapper.update(user,wrapper);
    }
}
