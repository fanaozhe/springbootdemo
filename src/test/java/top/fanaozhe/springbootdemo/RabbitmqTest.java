package top.fanaozhe.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author faz
 * @create 2020-10-27-10:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void directTest(){
        rabbitTemplate.convertAndSend("fanaozhe-queue","fanaozhe direct");
    }
}
