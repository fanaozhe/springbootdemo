package top.fanaozhe.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.fanaozhe.springbootdemo.service.SendSms;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
//跨域支持
@CrossOrigin
@RequestMapping("/sms")
public class SmsApiController {
    @Autowired
    private SendSms sendSms;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
 
 
    @GetMapping("/send/{phone}")
    public String code(@PathVariable("phone") String phone) {
        //调用发送方法（模拟真实业务redis）
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return phone + ":" + code + "已存在，还没过期";
        }
 
        //生成验证码并存储到redis中
        code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>();
        //name是配置在模板中的参数
        param.put("name", code);
 
        boolean isSend = sendSms.send(phone, param);
 
        if (isSend) {
            redisTemplate.opsForValue().set(phone, code, 1, TimeUnit.MINUTES);
            return phone + ":" + code + "发送成功！";
        } else {
            return "发送失败！";
        }
    }
}