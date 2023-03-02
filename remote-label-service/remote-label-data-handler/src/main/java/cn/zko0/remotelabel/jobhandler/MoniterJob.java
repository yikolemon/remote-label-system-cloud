package cn.zko0.remotelabel.jobhandler;

import cn.zko0.remotelabel.service.NoticeLogService;
import cn.zko0.remotelabel.service.RecordService;
import cn.zko0.remotelabel.service.SpaceStatusService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author duanfuqiang
 * @date 2023/3/1 22:49
 * @description
 */

@Component
@Slf4j
public class MoniterJob {

    @Autowired
    private NoticeLogService noticeLogService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private SpaceStatusService spaceStatusService;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("testHandler")
    public void demoJobHandler() throws Exception {

    }

}
