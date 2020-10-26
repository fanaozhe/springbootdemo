package top.fanaozhe.springbootdemo.mapper;

import org.apache.ibatis.annotations.*;
import top.fanaozhe.springbootdemo.entity.Student;

import java.util.List;

/**
 * @author faz
 * @create 2020-10-25-14:46
 */
@Mapper
public interface StudentMapper {
    @Insert("insert into t_student(name,age,sex) values(#{name},#{age},#{sex})")
    public int createStudent(Student student);

    @Select("select * from t_student")
    List<Student> findAll();

    @Delete("delete from t_student where id = #{id} ")
    int deleteStudent(Integer id);

    @Update("UPDATE t_student set name='朱艳迪' WHERE id =#{id}")
    int updateStudent(Integer id);
}
