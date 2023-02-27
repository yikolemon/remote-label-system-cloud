package cn.zko0.remotelabel.service;

import cn.zko0.remotelabel.entity.Label;

import java.util.List;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:03
 * @description
 */
public interface LabelService {

    public void labelRegister(Label label);

    public void labelListRegister(List<Label> labelList);

    public Long getRegisterNum(String collectorId);

}
