package top.fanaozhe.springbootdemo.dao;

import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import top.fanaozhe.springbootdemo.entity.Book;

import javax.transaction.Transactional;

/**
 * @author faz
 * @create 2020-10-25-12:23
 */
public interface BookDao extends JpaRepository<Book, Integer> {
    Book getBookById(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE t_book set book_name='发展之道' WHERE id =:id")
    void update(Integer id);


}
