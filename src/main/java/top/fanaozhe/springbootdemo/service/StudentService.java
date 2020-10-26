package top.fanaozhe.springbootdemo.service;

import top.fanaozhe.springbootdemo.entity.Student;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-25-14:52
 */
public interface StudentService {
    int creatStudent(Student student);

    List<Student> findAll();

    int deleteStudent(Integer id);

    int updateStudent(Integer id);
}
