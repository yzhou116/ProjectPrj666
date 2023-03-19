package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Score;

import java.util.Date;
import java.util.List;

public interface ScoreService {
    int add(Integer examCode, int stuId, String subject, Integer ptScore, Integer etScore, Integer score,  String AnswerDate, Integer scoreId);

    List<Score> findAll();

    IPage<Score> findById(Page page, Integer studentId);

    List<Score> findById(Integer studentId);

    List<Score> findByExamCode(Integer examCode);

    int findStudentIdbyUsername(String username);
}
