package top.fanaozhe.springbootdemo.config.rabbitmqconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/delayqueu")
public class DirectController {

    @Autowired
    private DirectSender sendMessage;

    @RequestMapping("/a")
    public String contextLoads() throws InterruptedException {
        for (int i = 1; i < 30; i++) {
            Thread.sleep(11000);
            sendMessage.messageSender(String.valueOf(i));
        }
        return"ok";
    }

}