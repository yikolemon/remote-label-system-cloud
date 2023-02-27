package cn.zko0.remotelabel.vo;

import cn.zko0.remotelabel.entity.Label;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author duanfuqiang
 * @date 2023/2/24 15:57
 * @description
 */
@Data
public class PublishResponse implements Serializable {

    //0:注册，1:监控
    private Integer msgType;

    //设备列表
    private List<Label> labelList;

    private Date reportTime;

    public boolean isRegisterMsg(){
        return msgType.equals(0);
    }

    public boolean isMonitorMsg(){
        return msgType.equals(1);
    }

}
