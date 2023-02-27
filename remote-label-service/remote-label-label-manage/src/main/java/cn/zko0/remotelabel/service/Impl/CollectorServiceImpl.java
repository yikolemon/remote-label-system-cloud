package cn.zko0.remotelabel.service.Impl;

import cn.zko0.remotelabel.entity.Collector;
import cn.zko0.remotelabel.mapper.CollectorMapper;
import cn.zko0.remotelabel.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author duanfuqiang
 * @date 2023/2/27 10:32
 * @description
 */
@Service
public class CollectorServiceImpl implements CollectorService {

    @Autowired
    private CollectorMapper collectorMapper;

    @Override
    public void addCollector(Collector collector) {
        collectorMapper.insert(collector);
    }

    @Override
    public Boolean getExist(String collectorId) {
        //不为空为存在true
        return collectorMapper.selectById(collectorId) != null;
    }

    @Override
    public void deleteCollector(Collector collector) {
        collectorMapper.deleteById(collector.getCollectorId());
    }

}
