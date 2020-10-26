package top.fanaozhe.springbootdemo.entity;

import lombok.Data;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-26-18:49
 */
@Data
public class Menu {
    private Integer id;
    private String pattern;
    private List<Role> roles;
}
