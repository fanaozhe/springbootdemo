package top.fanaozhe.springbootdemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.fanaozhe.springbootdemo.service.StudentService;

/**
 * @author faz
 * @create 2020-10-25-15:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {
    @Autowired
    StudentService studentService;
    @Test
    public void findAllTest() {
        int count = studentService.findAll().size();
        Assert.assertEquals(4,count);
    }
}
