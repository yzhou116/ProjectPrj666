package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExamManageMapper {
    @Select("select * from exam_manage")
    List<ExamManage> findAll();

    @Select("select * from exam_manage")
    IPage<ExamManage> findAll(Page page);

    @Select("select * from exam_manage where examCode = #{examCode}")
    ExamManage findById(Integer examCode);

    @Delete("delete from exam_manage where examCode = #{examCode}")
    int delete(Integer examCode);

    @Update("update exam_manage set description = #{description},source = #{source},paperId = #{paperId}," +
            "examDate = #{examDate},totalTime = #{totalTime},grade = #{grade},term = #{term}," +
            "major = #{major},institute = #{institute},totalScore = #{totalScore}," +
            "type = #{type},tips = #{tips} where examCode = #{examCode}")
    int update(ExamManage exammanage);

    @Options(useGeneratedKeys = true,keyProperty = "examCode")
    @Insert("insert into exam_manage(description,source,paperId,examDate,totalTime,grade,term,major,institute,totalScore,type,tips,teacher,student,ispublic, isSurvey, isPractice )" +
            " values(#{description},#{source},#{paperId},#{examDate},#{totalTime},#{grade},#{term},#{major},#{institute},#{totalScore},#{type},#{tips},#{teacher}, #{student}, #{ispublic}, #{issurvey},#{ispractice})")
    int add(ExamManage exammanage);


    @Select("select paperId from exam_manage order by paperId desc limit 1")
    ExamManage findOnlyPaperId();

    @Select("select * from exam_manage where teacher = #{username}")
    IPage<ExamManage> teacherfindAll(Page<ExamManage> page, String username);


    @Update("update exam_manage set student = #{jsonStudentLst} where examCode = #{examCode}")
    int addStudenToExam(String jsonStudentLst, String examCode);


    @Select("select student from exam_manage where examCode = #{examCode}")
    String findStudentInExam(String examCode);

    @Select("select * from exam_manage where teacher = #{teacherId} and isSurvey = 1")
    List<ExamManage> findSurveyByTeacherId(String teacherId);

    @Select("select * from (select * from exam_manage where teacher != #{userCode} ) as myalias where ispublic = 1")
    List<ExamManage> findByUserNameAndIsPublic(String userCode);

   // @Select("select * from exam_manage ")
   // List<ExamManage> findExamByTeacherName(String username);
}
