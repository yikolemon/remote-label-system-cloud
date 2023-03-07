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
public class PublishRequest implements Serializable {


    //设备列表
    private List<Label> labelList;

    private Date reportTime;


}
