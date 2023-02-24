package cn.zko0.remotelabel.netty.dao;

import cn.zko0.remotelabel.util.RedisKeyUtil;
import cn.zko0.remotelabel.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author duanfuqiang
 * @date 2023/2/24 14:13
 * @description
 */

@Component
public class CollectorDao {

    @Autowired
    private RedisUtil redisUtil;

    //限时20s在线，用于处理心跳包
    public void onlineLimitTime(String clientId){
        String key = RedisKeyUtil.NETTY_CLIENT_ID_KEY.getKey();
        redisUtil.setCacheObject(key+":"+clientId,1,30, TimeUnit.SECONDS);
    }

    public boolean checkOnline(String clientId){
        if (redisUtil.getCacheObject(RedisKeyUtil.NETTY_CLIENT_ID_KEY.getKey()+":"+clientId)==null) {
            return false;
        }else {
            return true;
        }
    }

    public void offLine(String clientId){
        redisUtil.deleteObject(RedisKeyUtil.NETTY_CLIENT_ID_KEY.getKey()+":"+clientId);
    }

    //延长redis中clientId对应的过期时间
    public void extendOnlinePermission(String clientId){
        redisUtil.expire(RedisKeyUtil.NETTY_CLIENT_ID_KEY.getKey()+":"+clientId,30,TimeUnit.SECONDS);
    }


}
