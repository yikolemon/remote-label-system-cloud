package cn.zko0.remotelabel.util;

import java.util.Random;

/**
 * @author duanfuqiang
 * @date 2023/2/16 11:25
 * @description
 */
public class CodeUtil {
    public static String getCode4(){
        return getCode(4);
    }

    public static String getCode(int length){
        char[] dictionary="QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        char[] temp=new char[length];
        for (int i = 0; i < length; i++) {
            temp[i]= dictionary[new Random().nextInt(dictionary.length)];
        }
        return new String(temp);
    }
}
