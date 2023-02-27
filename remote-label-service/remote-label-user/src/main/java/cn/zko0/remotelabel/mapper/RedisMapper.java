package cn.zko0.remotelabel.mapper;

import cn.zko0.remotelabel.util.RedisKeyUtils;
import cn.zko0.remotelabel.util.RedisUtils;
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
    private RedisUtils redisUtils;

    public String getRegisterCode(String email){
        return redisUtils.getCacheObject(RedisKeyUtils.REGISTER_CODE_KEY.getKey()+":"+email);
    }

    public void setRegisterCode(String email,String code){
        redisUtils.setCacheObject(RedisKeyUtils.REGISTER_CODE_KEY.getKey()+":"+email, code,10, TimeUnit.MINUTES);
    }

    public String getResetPwdCode(String email){
        return redisUtils.getCacheObject(RedisKeyUtils.RESET_PWD_CODE_KEY.getKey()+":"+email);
    }

    public void setResetPwdCode(String email,String code){
        redisUtils.setCacheObject(RedisKeyUtils.RESET_PWD_CODE_KEY.getKey()+":"+email, code,10, TimeUnit.MINUTES);
    }

}
