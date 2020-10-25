package top.fanaozhe.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanaozhe.springbootdemo.entity.User;
import top.fanaozhe.springbootdemo.service.UserService;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-24-23:15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/detail/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @RequestMapping("/list")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/add")
    public int createUser(){
        User user = new User();
        user.setId(5);
        user.setName("浙江农信");
        user.setMobile("8868");
        user.setEmail("zjrcu");
        return userService.createUser(user);
    }

    @RequestMapping("/delete/{id}")
    public int deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
