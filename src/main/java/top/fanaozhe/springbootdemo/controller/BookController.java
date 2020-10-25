package top.fanaozhe.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanaozhe.springbootdemo.entity.Book;
import top.fanaozhe.springbootdemo.service.BookService;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-25-12:25
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/getBookById/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @RequestMapping("/list")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @RequestMapping("/save")
    public void saveBook() {
        Book book = new Book();
        book.setName("银行理论");
        book.setAuthor("浙江农信");
        book.setPrice((float) 20);
        bookService.save(book);
    }

    @RequestMapping("/delete/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteById(id);
    }

    @RequestMapping("/update/{id}")
    public void updateBook(@PathVariable Integer id) {
       bookService.update(id);

    }
}
