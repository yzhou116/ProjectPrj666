package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from teacher")
    IPage<Teacher>  findAll(Page page);

    @Select("select * from teacher")
    public List<Teacher> findAll();

    @Select("select * from teacher where teacherId = #{teacherId}")
    public Teacher findById(Integer teacherId);

    @Delete("delete from teacher where username = #{teacherId}")
    public int deleteByUsrName(String username);

    @Update("update teacher set teacherName = #{tname},sex = #{tsex}," +
            "tel = #{tel}, email = #{temail},pwd = #{tpwd},cardId = #{card}," +
            "institute = #{tins},type = #{type} where username = #{oringalEmail}")
    public int update(String tname,String temail,String tsex,String tins,String tpwd,String card,String tel,String type,String oringalEmail);

    @Options(useGeneratedKeys = true,keyProperty = "teacherId")
    @Insert("insert into teacher(username,teacherName,sex,tel,email,pwd,cardId,role,type,institute) " +
            "values(#{teacherUsername},#{teacherName},#{sex},#{tel},#{email},#{pwd},#{cardId},#{role},#{type},#{institute})")
    public int add(Teacher teacher);
}
