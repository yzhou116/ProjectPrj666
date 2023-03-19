package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import com.exam.entity.Score;
import com.exam.mapper.ExamManageMapper;
import com.exam.mapper.ScoreMapper;
import com.exam.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private ExamManageMapper examManageMapper;

    @Override
    public int add(Integer examCode, int stuId, String subject, Integer ptScore, Integer etScore, Integer score,  String AnswerDate, Integer scoreId) {
        if(score == null){
            ExamManage examManage = examManageMapper.findById(examCode);
            score = examManage.getTotalScore();
        }

        return scoreMapper.add(examCode, stuId, subject,ptScore,etScore,score,AnswerDate,scoreId);
    }

    @Override
    public List<Score> findAll() {
        return scoreMapper.findAll();
    }

    @Override
    public IPage<Score> findById(Page page, Integer studentId) {
        return scoreMapper.findById(page, studentId);
    }

    @Override
    public List<Score> findById(Integer studentId) {
        return scoreMapper.findById(studentId);
    }

    @Override
    public List<Score> findByExamCode(Integer examCode) {
        return scoreMapper.findByExamCode(examCode);
    }

    public List<Score> findScoreForDisplayRes(Integer examCode){
        return scoreMapper.findByExamCodeForResDisplay(examCode);
    }



    @Override
    public int findStudentIdbyUsername(String username) {
        return scoreMapper.findStudentIdByUserName(username);
    }
}
