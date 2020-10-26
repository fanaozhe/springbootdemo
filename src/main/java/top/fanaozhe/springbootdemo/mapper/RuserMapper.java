package top.fanaozhe.springbootdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.fanaozhe.springbootdemo.entity.Role;
import top.fanaozhe.springbootdemo.entity.Ruser;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-24-14:06
 */
@Mapper
public interface RuserMapper {
    /*通过用户名查找用户*/
    Ruser findUserByUsername(String username);

    /*通过用户id查找用户角色*/
    List<Role> getUserRolesByUid(Integer id);
}
