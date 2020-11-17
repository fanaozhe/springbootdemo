package top.fanaozhe.springbootdemo.config.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


/**
 * @author faz
 * @create 2020-10-27-10:35
*/

@Configuration
public class RabbitDirectConfig {

    @Value("${spring.rabbitmq.exchangename}")
    private String exchange;
    @Value("${spring.rabbitmq.delayexchangename}")
    private String delayexchange;
    @Value("${spring.rabbitmq.queuename}")
    private String queue;
    @Value("${spring.rabbitmq.delayqueuename}")
    private String delayqueue;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;
    @Value("${spring.rabbitmq.delayroutingkey}")
    private String delayroutingKey;

    //正常消费队列绑定的交换机
    //参数：交换机名字、重启后是否依然有效、长期未使用时是否删除；
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange, true, false);
    }

    //创建延迟队列绑定的交换机
    @Bean
    DirectExchange delayExchange() {
        return new DirectExchange(delayexchange, true, false);
    }

    //创建正常消费队列
    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }

    //创建延迟队列
    @Bean
    Queue delayQueue() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", exchange);
        args.put("x-dead-letter-routing-key", routingKey);
        return new Queue(delayqueue, true, false, false, args);
    }

    //绑定实际消费者到交换机
    @Bean
    Binding binding() {
        //return new Binding(queue, Binding.DestinationType.QUEUE,exchange,routingKey,null);
        return BindingBuilder.bind(queue()).to(directExchange()).with(routingKey);
    }

    //绑定延迟队列到交换机
    @Bean
    Binding delaybinding() {
        //return new Binding(delayqueue, Binding.DestinationType.QUEUE, delayexchange,delayroutingKey,null);
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(delayroutingKey);
    }
}
