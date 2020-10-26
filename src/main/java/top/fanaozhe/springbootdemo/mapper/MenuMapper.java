package top.fanaozhe.springbootdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.fanaozhe.springbootdemo.entity.Menu;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-26-18:48
 */
@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();
}
