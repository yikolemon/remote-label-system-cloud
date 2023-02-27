package cn.zko0.remotelabel;

import cn.zko0.remotelabel.entity.Label;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:31
 * @description
 */

@RequestMapping("/label")
public interface LabelApi {

    @RequestMapping("/register")
    public void insertLabelList(@RequestBody List<Label> labelList);

    @RequestMapping("/getRegisterNum")
    public Long getRegisterNum(String collectorId);

}
