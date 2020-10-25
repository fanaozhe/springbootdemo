package top.fanaozhe.springbootdemo.service;

import top.fanaozhe.springbootdemo.entity.User;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-24-23:06
 */
public interface UserService {
    User getUser(Long id);

    List<User> findAll();

    int createUser(User user);

    int deleteUser(Long id);
}
