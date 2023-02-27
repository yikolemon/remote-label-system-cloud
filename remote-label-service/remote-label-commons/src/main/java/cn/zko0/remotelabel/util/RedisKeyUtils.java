package cn.zko0.remotelabel.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duanfuqiang
 * @date 2023/2/22 22:01
 * @description
 */

@AllArgsConstructor
@Getter
public enum RedisKeyUtils {


    REGISTER_CODE_KEY("registCheckCode"),
    RESET_PWD_CODE_KEY("resetPwdCheckCode"),

    NETTY_CLIENT_ID_KEY("nettyClientId");

    private String key;

}
