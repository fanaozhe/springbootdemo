package top.fanaozhe.springbootdemo;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanaozhe.springbootdemo.config.rabbitmqconfig.DirectSender;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@RestController
@RequestMapping("/api")
public class RabbitmqdemoApplicationTests {
 
    @Autowired
    private DirectSender directSender;
 
    //@Test
    @RequestMapping("/a")
    public void contextLoads() {
        for(int i=1;i<=20;i++){
 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            directSender.messageSender("测试消息"+i);
 
        }
 
 
    }
 
}