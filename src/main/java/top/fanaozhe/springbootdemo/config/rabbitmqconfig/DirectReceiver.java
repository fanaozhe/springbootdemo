package top.fanaozhe.springbootdemo.config.rabbitmqconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.fanaozhe.springbootdemo.utils.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author faz
 * @create 2020-10-27-10:48
 */

@Component
@Slf4j
public class DirectReceiver {

    @RabbitHandler
    @RabbitListener(queues = "direct-queue-faz")
    //@RabbitListener(queues = "direct-delayqueue-faz")
    public void handler(String message) {
       /* BigDecimal st =new BigDecimal("-"+message);
        BigDecimal end =new BigDecimal(String.valueOf(new Date().getTime()));
        log.info("消费者接收消息:" + end.add(st).toString());*/
        //log.info("消费者接收到消息:" + System.currentTimeMillis());
        log.info("消费者接收到消息:" + message+"--" + DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
