package top.fanaozhe.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fanaozhe.springbootdemo.entity.User;
import top.fanaozhe.springbootdemo.mapper.UserMapper;
import top.fanaozhe.springbootdemo.service.UserService;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-24-23:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(Long id) {
        return userMapper.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int createUser(User user) {
        return userMapper.createUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }
}



