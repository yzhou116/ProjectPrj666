package com.exam.mapper;

import com.exam.entity.Admin;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LoginMapper {

    @Select("select adminId,adminName,sex,tel,email,cardId,role from admin where adminId = #{username} and pwd = #{password}")
    public Admin adminLogin(String username, String password);

    @Select("select teacherId,teacherName,institute,sex,tel,email,cardId," +
            "type,role from teacher where username = #{username} and pwd = #{password}")
    public Teacher teacherLogin(String username, String password);

    @Select("select studentId,studentName,grade,major,clazz,institute,tel," +
            "email,cardId,sex,role from student where username = #{username} and pwd = #{password}")
    public Student studentLogin(String username,String password);

    @Update("update student set pwd = #{password} where username = #{username}")
   // @Select("select * from  student where username = #{username}")
    public int updatestudentLogin(String username, String password);

    @Update("update teacher set pwd = #{password} where username = #{username}")
    // @Select("select * from  student where username = #{username}")
    public int updateTeacherLogin(String username, String password);


    @Update("update admin set pwd = #{password} where username = #{username}")
    // @Select("select * from  student where username = #{username}")
    public int updateAdminLogin(String username, String password);


    @Select("select * from student where username = #{username}")
    public Student getStudentById(String username);

    @Select("select * from teacher where username = #{username}")
    public Teacher getTeacherById(String username);

    @Select("select * from admin where username = #{username}")
    public Admin getAdminById(String username);

}
