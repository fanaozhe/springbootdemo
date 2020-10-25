package top.fanaozhe.springbootdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.fanaozhe.springbootdemo.entity.User;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-24-22:52
 */
@Mapper
public interface UserMapper {
    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public User findOne(Long id);

    /**
     * 查找所有用户
     * @return
     */
    public List<User> findAll();

    /**
     * 添加用户
     * @param user
     * @return
     */
    int createUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Long id);


}
