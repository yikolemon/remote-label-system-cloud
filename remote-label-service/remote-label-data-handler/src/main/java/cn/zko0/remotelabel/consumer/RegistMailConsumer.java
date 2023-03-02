package cn.zko0.remotelabel.consumer;


import cn.zko0.remotelabel.entity.Label;
import cn.zko0.remotelabel.entity.Record;
import cn.zko0.remotelabel.mapper.RecordMapper;
import cn.zko0.remotelabel.service.plus.RecordWapperImpl;
import cn.zko0.remotelabel.util.MQUtils;
import cn.zko0.remotelabel.vo.PublishRequest;
import cn.zko0.remotelabel.vo.PublishResponse;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class RegistMailConsumer {

    @Autowired
    private RecordWapperImpl recordWapper;

    //普通队列名称
    public static final String  LABEL_REPORT_QUEUE= MQUtils.LABEL_REPORT_QUEUE;

    /**
     * 插入到数据库
     * @param message
     */
    @RabbitListener(queues = LABEL_REPORT_QUEUE)
    public void handleMoniterMsg(Message message){
        String content = new String(message.getBody());
        PublishRequest publishRequest = JSON.parseObject(content, PublishRequest.class);
        List<Label> labelList = publishRequest.getLabelList();
        List<Record> recordList=new ArrayList<>(labelList.size());
        for (int i = 0; i < labelList.size(); i++) {
            recordList.add(new Record(null,labelList.get(i).getLabelId(),labelList.get(i).getCreateTime()));
        }
        recordWapper.saveBatch(recordList);
    }

}
