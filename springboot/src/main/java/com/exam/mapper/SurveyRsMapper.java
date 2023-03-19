package com.exam.mapper;

import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.entity.SurveyReturn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SurveyRsMapper {

    @Insert("insert into surveyRs(id, examCode, studentId, answerResult,answerDate) values (#{id}, #{examCode}, #{useremail}, #{result}, #{answerDate})")
    int add(String id,Integer examCode, String useremail, String result, String answerDate);

    @Select("select answerResult from surveyRs where examCode = #{examCode}")
    String findResByExamCode(Integer examCode);

    @Select("select * from surveyRs where examCode = #{examCode}")
    List<SurveyReturn> findSurveyByExamCode(Integer examCode);


    @Select("select * from multi_question where questionId = #{questionId}")
    MultiQuestion findMultiQuestion(Integer questionId);

    @Select("select * from fill_question where questionId = #{questionId}")
    FillQuestion findFillQuestion(Integer questionId);


    @Select("select * from judge_question where questionId = #{questionId}")
    JudgeQuestion findJuedgeQuestion(Integer questionId);

}
