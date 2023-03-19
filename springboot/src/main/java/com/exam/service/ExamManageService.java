package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ExamManageService {


    List<ExamManage> findAll();
    IPage<ExamManage> findAll(Page<ExamManage> page);

    ExamManage findById(Integer examCode);

    int delete(Integer examCode);

    int update(ExamManage exammanage);

    int add(ExamManage exammanage);

    ExamManage findOnlyPaperId();

    int addStudent(String examCode, String username) throws JsonProcessingException;

    List<ExamManage> findExamByUserName(List<ExamManage> examManage,String userCode);

    List<ExamManage> findExamByUserNameWithIsPublic(String userCode);

    IPage<ExamManage> teacherfindAll(Page<ExamManage> examManage, String username);

    List<ExamManage> findExamByTeacherName(String username);
}
