package cn.zko0.remotelabel.util;

import cn.zko0.remotelabel.vo.PublishRequest;
import com.alibaba.fastjson.JSON;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:42
 * @description
 */
public class PublishMsgSerializeUtils {

    public static PublishRequest json2Obj(String json){
        return JSON.parseObject(json, PublishRequest.class);
    }

    public static String obj2Json(PublishRequest publishRequest){
        return JSON.toJSONString(publishRequest);
    }


}
