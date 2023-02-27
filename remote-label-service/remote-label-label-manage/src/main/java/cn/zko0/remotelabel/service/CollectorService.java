package cn.zko0.remotelabel.service;

import cn.zko0.remotelabel.entity.Collector;
import org.springframework.stereotype.Service;

/**
 * @author duanfuqiang
 * @date 2023/2/27 10:29
 * @description
 */
public interface CollectorService {

    public void addCollector(Collector collector);

    public void deleteCollector(Collector collector);

    public Boolean getExist(String collectorId);

}
