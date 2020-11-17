package top.fanaozhe.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.fanaozhe.springbootdemo.config.rabbitmqconfig.DirectSender;

/**
 * @author faz
 * @create 2020-10-27-20:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayRabbitmqTest {
    @Autowired
    DirectSender directSender;

    @Test
    public void contextLoads() throws InterruptedException {
        for (int i=1;i<=50;i++){
            Thread.sleep(500);
            directSender.messageSender("测试消息"+i);
        }
    }

}
