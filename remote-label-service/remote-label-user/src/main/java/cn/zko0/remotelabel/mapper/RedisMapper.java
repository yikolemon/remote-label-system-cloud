package cn.zko0.remotelabel.mapper;

import cn.zko0.remotelabel.util.RedisKeyUtil;
import cn.zko0.remotelabel.util.RedisUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author duanfuqiang
 * @date 2023/2/22 22:11
 * @description
 */
@Component
public class RedisMapper {

    @Autowired
    private RedisUtil redisUtil;

    public String getRegisterCode(String email){
        return redisUtil.getCacheObject(RedisKeyUtil.REGISTER_CODE_KEY.getKey()+":"+email);
    }

    public void setRegisterCode(String email,String code){
        redisUtil.setCacheObject(RedisKeyUtil.REGISTER_CODE_KEY.getKey()+":"+email, code,10, TimeUnit.MINUTES);
    }

    public String getResetPwdCode(String email){
        return redisUtil.getCacheObject(RedisKeyUtil.RESET_PWD_CODE_KEY.getKey()+":"+email);
    }

    public void setResetPwdCode(String email,String code){
        redisUtil.setCacheObject(RedisKeyUtil.RESET_PWD_CODE_KEY.getKey()+":"+email, code,10, TimeUnit.MINUTES);
    }

}
