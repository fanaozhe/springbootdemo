package top.fanaozhe.springbootdemo.config.rabbitmqconfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author faz
 * @create 2020-10-27-10:48
 */
@Component
public class DirectReceiver {

    @RabbitListener(queues = "direct-queue-faz")
    public void handler(String message){
        System.out.println("消费者接收消息:"+message);
    }
}
