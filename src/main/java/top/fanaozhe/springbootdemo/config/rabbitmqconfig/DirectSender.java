package top.fanaozhe.springbootdemo.config.rabbitmqconfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author faz
 * @create 2020-10-27-11:37
 */

@Component
public class DirectSender {
    /*//直路由实现消息发收
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
    }*/

    @Value("${spring.rabbitmq.delayexchangename}")
    private String delayexchange;
    @Value("${spring.rabbitmq.delayroutingkey}")
    private String delayroutingKey;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void messageSender(String task) {
        rabbitTemplate.convertAndSend(delayexchange, delayroutingKey, task, message -> {
            //设置消息过期时间为10s,过期后转发到指定路由；
            message.getMessageProperties().setExpiration("10000");
            return message;
        });
    }
}
