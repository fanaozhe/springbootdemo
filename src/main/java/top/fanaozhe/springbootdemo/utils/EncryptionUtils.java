package top.fanaozhe.springbootdemo.utils;

import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author faz
 * @create 2020-10-26-15:49
 */
@RestController
@RequestMapping("/a")
public class EncryptionUtils {
    @RequestMapping("/b/{password}")
    public String reg(@PathVariable String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(password);
        return encodePasswd;
    }
}
