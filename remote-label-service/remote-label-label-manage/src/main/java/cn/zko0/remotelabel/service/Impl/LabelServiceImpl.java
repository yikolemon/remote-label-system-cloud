package cn.zko0.remotelabel.service.Impl;

import cn.zko0.remotelabel.entity.Label;
import cn.zko0.remotelabel.entity.User;
import cn.zko0.remotelabel.mapper.LabelMapper;
import cn.zko0.remotelabel.service.LabelService;
import cn.zko0.remotelabel.service.plus.LabelWapperImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duanfuqiang
 * @date 2023/2/27 14:08
 * @description
 */

@Service
public class LabelServiceImpl implements LabelService {


    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelWapperImpl labelWapper;

    @Override
    public void labelRegister(Label label) {
        labelMapper.insert(label);
    }

    @Override
    public void labelListRegister(List<Label> labelList) {
        labelWapper.saveBatch(labelList);
    }

    @Override
    public Long getRegisterNum(String collectorId) {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.eq("collector_id",collectorId);
        return  labelMapper.selectCount(wrapper);
    }
}
