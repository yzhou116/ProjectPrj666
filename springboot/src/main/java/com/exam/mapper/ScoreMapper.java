package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ScoreMapper {

   // @Options(useGeneratedKeys = true,keyProperty = "scoreId")
    @Insert("insert into score(examCode,studentId,subject,ptScore,etScore,score,answerDate) values(#{examCode},#{stuId},#{subject},#{ptScore},#{etScore},#{score},#{answerDate})")
    int add(Integer examCode, int stuId, String subject, Integer ptScore, Integer etScore, Integer score,  String answerDate, Integer scoreId);

    @Select("select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score order by scoreId desc")
    List<Score> findAll();

    @Select("select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score where studentId = #{studentId} order by scoreId desc")
    IPage<Score> findById(Page<?> page, Integer studentId);


    @Select("select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score where studentId = #{studentId}")
    List<Score> findById(Integer studentId);


    @Select("select max(etScore) as etScore from score where examCode = #{examCode} group by studentId")
    List<Score> findByExamCode(Integer examCode);

    @Select("select scoreId,examCode,studentId,subject,ptScore,etScore,score,answerDate from score where examCode = #{examCode}")
    List<Score> findByExamCodeForResDisplay(Integer examCode);

    @Select("select studentId from student where username = #{username}")
    int findStudentIdByUserName(String username);
}
