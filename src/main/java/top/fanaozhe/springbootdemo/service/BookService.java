package top.fanaozhe.springbootdemo.service;

import top.fanaozhe.springbootdemo.entity.Book;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-25-12:30
 */
public interface BookService {

    Book getBookById(Integer id);

    List<Book> findAll();

    void save(Book book);

    void deleteById(Integer id);

    void update(Integer id);

}
