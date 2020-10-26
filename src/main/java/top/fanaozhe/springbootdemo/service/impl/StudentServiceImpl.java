package top.fanaozhe.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.fanaozhe.springbootdemo.entity.Student;
import top.fanaozhe.springbootdemo.mapper.StudentMapper;
import top.fanaozhe.springbootdemo.service.StudentService;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-25-14:53
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public int creatStudent(Student student) {
        return studentMapper.createStudent(student);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public int deleteStudent(Integer id) {
        return studentMapper.deleteStudent(id);
    }

    @Override
    public int updateStudent(Integer id) {
        return studentMapper.updateStudent(id);
    }
}
