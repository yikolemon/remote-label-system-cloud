package cn.zko0.remotelabel.controller;

import cn.zko0.remotelabel.entity.Label;
import cn.zko0.remotelabel.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:21
 * @description
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping("/register")
    public void insertLabelList(@RequestBody List<Label> labelList){
        labelService.labelListRegister(labelList);
    }

    @RequestMapping("/getRegisterNum")
    public Long getRegisterNum(String collectorId){
        return labelService.getRegisterNum(collectorId);
    }

}
