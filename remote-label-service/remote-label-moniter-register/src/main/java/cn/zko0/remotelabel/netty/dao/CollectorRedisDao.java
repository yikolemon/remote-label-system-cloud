package cn.zko0.remotelabel.netty.dao;

import cn.zko0.remotelabel.util.RedisKeyUtils;
import cn.zko0.remotelabel.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author duanfuqiang
 * @date 2023/2/24 14:13
 * @description
 */

@Component
public class CollectorRedisDao {

    @Autowired
    private RedisUtils redisUtils;

    //限时20s在线，用于处理心跳包
    public void onlineLimitTime(String clientId){
        String key = RedisKeyUtils.NETTY_CLIENT_ID_KEY.getKey();
        redisUtils.setCacheObject(key+":"+clientId,1,30, TimeUnit.SECONDS);
    }

    public boolean checkOnline(String clientId){
        if (redisUtils.getCacheObject(RedisKeyUtils.NETTY_CLIENT_ID_KEY.getKey()+":"+clientId)==null) {
            return false;
        }else {
            return true;
        }
    }

    public void offLine(String clientId){
        redisUtils.deleteObject(RedisKeyUtils.NETTY_CLIENT_ID_KEY.getKey()+":"+clientId);
    }

    //延长redis中clientId对应的过期时间
    public void extendOnlinePermission(String clientId){
        redisUtils.expire(RedisKeyUtils.NETTY_CLIENT_ID_KEY.getKey()+":"+clientId,30,TimeUnit.SECONDS);
    }

}
