package cn.zko0.remotelabel.service.impl;

import cn.zko0.remotelabel.mapper.UserMapper;
import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
