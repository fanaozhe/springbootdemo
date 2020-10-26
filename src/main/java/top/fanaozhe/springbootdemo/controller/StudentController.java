package top.fanaozhe.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fanaozhe.springbootdemo.entity.Student;
import top.fanaozhe.springbootdemo.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author faz
 * @create 2020-10-25-14:50
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/add")
    public String creatStudent() {
        Map<String, String> result = new HashMap<>();
        Student student = new Student();
        student.setName("范奥哲");
        student.setAge("25");
        student.setSex("男");
        int count = studentService.creatStudent(student);
        if (count > 0) {
            result.put("status", "添加成功");
        } else {
            result.put("status", "添加失败");
        }
        return result.get("status");
    }

    @RequestMapping("/list")
    public List<Student> findAll() {
        List<Student> list = studentService.findAll();
        return list;
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        Map<String, String> result = new HashMap<>();
        int count = studentService.deleteStudent(id);
        if (count > 0) {
            result.put("status", "删除成功");
        } else {
            result.put("status", "删除失败");
        }
        return result.get("status");

    }

    @RequestMapping("/update/{id}")
    public String updateStudent(@PathVariable Integer id) {
        Map<String, String> result = new HashMap<>();
        int count = studentService.updateStudent(id);
        if (count > 0) {
            result.put("status", "修改成功");
        } else {
            result.put("status", "修改失败");
        }
        return result.get("status");

    }
}
