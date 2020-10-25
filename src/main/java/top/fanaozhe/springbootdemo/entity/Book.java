package top.fanaozhe.springbootdemo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author faz
 * @create 2020-10-25-11:56
 */
@Data
@Entity(name ="t_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_name",nullable = false)
    private String name;
    private String author;
    private Float price;
    @Transient
    private String description;
}
