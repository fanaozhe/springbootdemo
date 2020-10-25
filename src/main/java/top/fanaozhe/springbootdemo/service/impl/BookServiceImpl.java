package top.fanaozhe.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fanaozhe.springbootdemo.dao.BookDao;
import top.fanaozhe.springbootdemo.entity.Book;
import top.fanaozhe.springbootdemo.service.BookService;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-25-12:30
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;
    @Override
    public Book getBookById(Integer id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookDao.deleteById(id);
    }

    @Override
    public void update(Integer id) {
        bookDao.update(id);
    }


}
