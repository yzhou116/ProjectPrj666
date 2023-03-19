package com.exam.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Score {
    private Integer examCode;

    private String studentId;

    private String subject;

    private Integer ptScore;

    private Integer etScore;

    private Integer score;

    private Integer scoreId;

    private String answerDate;




    public Integer getExamCode() {
        return examCode;
    }

    public void setExamCode(Integer examCode) {
        this.examCode = examCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getPtScore() {
        return ptScore;
    }

    public void setPtScore(Integer ptScore) {
        this.ptScore = ptScore;
    }

    public Integer getEtScore() {
        return etScore;
    }

    public void setEtScore(Integer etScore) {
        this.etScore = etScore;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }


}