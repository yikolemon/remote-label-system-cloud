package cn.zko0.remotelabel.util;

import cn.zko0.remotelabel.vo.PublishResponse;
import com.alibaba.fastjson.JSON;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:42
 * @description
 */
public class PublishMsgSerializeUtils {

    public static PublishResponse json2Obj(String json){
        return JSON.parseObject(json, PublishResponse.class);
    }

    public static String obj2Json(PublishResponse publishResponse){
        return JSON.toJSONString(publishResponse);
    }


}
