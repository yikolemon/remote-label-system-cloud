package cn.zko0.remotelabel.util;

import cn.dev33.satoken.secure.SaSecureUtil;

/**
 * @author duanfuqiang
 * @date 2023/2/15 22:28
 * @description
 */
public class AuthUtil {

    public static boolean checkPassword(String webPassword,String dbPassword){
        return dbPassword==encryption(webPassword);
    }

    /**
     * 加密
     * @return
     */

    public static String encryption(String webPassword){
        String temp = SaSecureUtil.md5(webPassword);
        String res = SaSecureUtil.sha256("salt" + temp + "salt");
        return res;
    }

}
