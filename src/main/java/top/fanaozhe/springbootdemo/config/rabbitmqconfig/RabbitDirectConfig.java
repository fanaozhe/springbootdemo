package top.fanaozhe.springbootdemo.config.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author faz
 * @create 2020-10-27-10:35
 */
@Configuration
public class RabbitDirectConfig {

    @Value("${spring.rabbitmq.exchangename}")
    private String exchangeName;
    @Value("${spring.rabbitmq.queuename}")
    private String queueName;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }

    //参数：交换机名字、重启后是否依然有效、长期未使用时是否删除；
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(routingKey);
    }
}
