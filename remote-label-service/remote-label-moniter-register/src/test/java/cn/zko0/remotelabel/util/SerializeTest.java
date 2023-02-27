package cn.zko0.remotelabel.util;

import cn.zko0.remotelabel.vo.PublishResponse;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author duanfuqiang
 * @date 2023/2/27 16:21
 * @description
 */
public class SerializeTest {

    @Test
    public void test1(){
        HashMap<String, String> map = new HashMap<>();
        map.put("msgType","0");
        String s = JSON.toJSONString(map);
        PublishResponse publishResponse = JSON.parseObject(s, PublishResponse.class);
        System.out.println(publishResponse);
    }

}
