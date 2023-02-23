package cn.zko0.remotelabel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.zko0.remotelabel.entity.Role;
import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.mail.MailRequest;
import cn.zko0.remotelabel.mymapper.MyUserMapper;
import cn.zko0.remotelabel.mapper.RedisMapper;
import cn.zko0.remotelabel.mapper.RoleMapper;
import cn.zko0.remotelabel.mapper.UserMapper;
import cn.zko0.remotelabel.service.SendMailService;
import cn.zko0.remotelabel.service.UserService;
import cn.zko0.remotelabel.util.AuthUtil;
import cn.zko0.remotelabel.util.CodeUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.UniqueElements;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MyUserMapper myUserMapper;

    @Autowired
    private RedisMapper redisMapper;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public SaResult doLogin(User user) {
        User dbUser = myUserMapper.getUserByEmail(user.getEmail());
        if (dbUser==null){
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
    public String sendRegisterEmailCode(String email) {
        //发送验证码
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSendTo(email);
        mailRequest.setSubject("注册验证码");
        String code = CodeUtil.getCode4();
        mailRequest.setText(code);
        sendMailService.sendSimpleMail(mailRequest);
        //保存到redis，设置三分钟超时
        redisMapper.setRegisterCode(email,code);
        return code;
    }

    @Override
    public String sendResetPwdEmailCode(String email) {
        //发送验证码
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSendTo(email);
        mailRequest.setSubject("重置密码验证码");
        String code = CodeUtil.getCode4();
        mailRequest.setText(code);
        sendMailService.sendSimpleMail(mailRequest);
        //保存到redis，设置三分钟超时
        redisMapper.setResetPwdCode(email,code);
        return code;
    }

    @Override
    public SaResult resetPassword(User user) {
        String resetPwdCode = redisMapper.getResetPwdCode(user.getEmail());
        if (resetPwdCode.isEmpty()||!resetPwdCode.equals(user.getCode())){
            return SaResult.error("验证码错误");
        }else {
            User dbUser = myUserMapper.getUserByEmail(user.getEmail());
            if (AuthUtil.checkPassword(user.getNewPassword(),dbUser.getPassword())) {
                return SaResult.error("新密码不能与原密码相同");
            }else {
                //密码加密,放入password中
                user.setPassword(AuthUtil.encryption(user.getNewPassword()));
                myUserMapper.updatePwdByEmail(user);
                return SaResult.ok("密码更新成分");
            }
        }
    }

    @Override
    public String getRoleById(String id) {
        User dbUser = userMapper.selectById(id);
        Integer roleId = dbUser.getRoleId();
        Role role = roleMapper.selectById(roleId);
        return role.getType();
    }

    @Override
    public String getRoleByEmail(String email) {
        User dbUser = myUserMapper.getUserByEmail(email);
        Integer roleId = dbUser.getRoleId();
        Role role = roleMapper.selectById(roleId);
        return role.getType();
    }

    @Override
    public SaResult emailRegister(User user) {
        String redisCode = redisMapper.getRegisterCode(user.getEmail());
        String code=user.getCode();
        if (StringUtils.isEmpty(user.getEmail())){
            return SaResult.error("邮箱不能为空");
        }
        if (myUserMapper.getUserByEmail(user.getEmail())!=null){
            return SaResult.error("用户已存在");
        }
        if (user.getPassword()==null){
            return SaResult.error("密码不能为空");
        }
        if (redisCode.equals(code)){
            user.setPassword(AuthUtil.encryption(user.getPassword()));
            userMapper.insert(user);
            return SaResult.ok("注册成功");
        }else {
            return SaResult.error("验证码错误");
        }
    }



}
