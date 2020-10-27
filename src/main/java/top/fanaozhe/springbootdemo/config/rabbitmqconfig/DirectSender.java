package top.fanaozhe.springbootdemo.config.rabbitmqconfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author faz
 * @create 2020-10-27-11:37
 */
@RestController
@RequestMapping("/rabbitmq")
public class DirectSender {

    @Value("${spring.rabbitmq.exchangename}")
    private String exchangeName;
    @Value("${spring.rabbitmq.queuename}")
    private String queueName;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @RequestMapping("/sender")
    public String messageSender(){
        String message = "测试消息！！！";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,message);
        return "ok";
    }
}
