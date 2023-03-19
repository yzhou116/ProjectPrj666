package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AnswerMapper {
  @Select("select question, subject, score, section,level, \"Multi Question\" as type from multi_question where teacher = #{username}" +
           "union select  question, subject, score, section,level, \"Judge Question\" as type  from judge_question  where teacher = #{username}" +
          "union select  question, subject, score, section,level, \"Fill Question\" as type from fill_question  where teacher = #{username}")
    IPage<AnswerVO> findAll(Page page, String username);
}
