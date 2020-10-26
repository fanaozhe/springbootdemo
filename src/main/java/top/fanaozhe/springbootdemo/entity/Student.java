package top.fanaozhe.springbootdemo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author faz
 * @create 2020-10-25-11:56
 */
@Data
@Entity(name ="t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String age;
    private String sex ;
}
