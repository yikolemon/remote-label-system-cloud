package cn.zko0.remotelabel.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建类并
 */

@Configuration
public class MQConfig {

    //普通交换机名称
    public static final String  LABEL_REPORT_EXCHANGE="labelReportExchange";
    //普通队列名称
    public static final String  LABEL_REPORT_QUEUE="labelReportQueue";

    public static final String routingKey="key";

    @Bean("labelReportExchange")
    public DirectExchange exchangeA(){
        return new DirectExchange(LABEL_REPORT_EXCHANGE);
    }

    @Bean("labelReportQueue")
    public Queue queueA(){
/*        Map<String,Object> map=new HashMap<>(1);
        //设置ttl 单位ms,120s过期
        map.put("x-message-ttl",120000);*/
        //durable设置了持久化
        return QueueBuilder.durable(LABEL_REPORT_QUEUE).build();
    }

    @Bean
    public Binding queueAexhcangeA(@Qualifier("labelReportQueue") Queue queue,
                                  @Qualifier("labelReportExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(routingKey);
    }

    //创建初始化RabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        rabbitAdmin.declareExchange(exchangeA());
        rabbitAdmin.declareQueue(queueA());
        return rabbitAdmin;
    }

}
