package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {


    @Select("select * from student")
    IPage<Student> findAll(Page page);

    @Select("select * from student where studentId = #{studentId}")
    Student findById(Integer studentId);

    @Delete("delete from student where studentId = #{studentId}")
    int deleteById(Integer studentId);


    @Delete("delete from student where username = #{username}")
    int deleteByUserName(String username);


    @Update("update student set studentName = #{studentName},grade = #{grade},major = #{major},clazz = #{clazz}," +
            "institute = #{institute},tel = #{tel},email = #{email},pwd = #{pwd},cardId = #{cardId},sex = #{sex},role = #{role} " +
            "where studentId = #{studentId}")
    int update(Student student);


    @Update("update student set pwd = #{pwd} where studentId = #{studentId}")
    int updatePwd(Student student);


    @Options(useGeneratedKeys = true,keyProperty = "studentId")
    @Insert("insert into student(username,studentName,grade,major,clazz,institute,tel,email,pwd,cardId,sex,role) values " +
            "(#{studentUsername},#{studentName},#{grade},#{major},#{clazz},#{institute},#{tel},#{email},#{pwd},#{cardId},#{sex},#{role})")
    int add(Student student);

    @Update("update student set studentName = #{sname}," +
            "institute = #{ins},tel = #{tel},email = #{semail},pwd = #{spwd},cardId = #{card},sex = #{ssex}" +
            "where username = #{oringalEmail}")
    int updateStudent(String sname,String semail ,String ssex,String ins, String spwd,String card,String tel,String oringalEmail);
}
