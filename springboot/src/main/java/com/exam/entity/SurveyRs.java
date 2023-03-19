package com.exam.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
@Data
public class SurveyRs {
    private Integer examCode;
    private String studentEmail;
    private List<String> topic1Answer;
    private List<List<String>> fillAnswer;
    private List<String> judgeAnswer;
    private String answerDate;

    private List<MultiQuestion> multiQuestionList;
    private List<FillQuestion> fillQuestionList;
    private List<JudgeQuestion> judgeQuestionsList;

    public Integer getExamCode() {
        return examCode;
    }

    public void setExamCode(Integer examCode) {
        this.examCode = examCode;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<String> getTopic1Answer() {
        return topic1Answer;
    }

    public void setTopic1Answer(List<String> topic1Answer) {
        this.topic1Answer = topic1Answer;
    }

    public List<List<String>> getFillAnswer() {
        return fillAnswer;
    }

    public void setFillAnswer(List<List<String>> fillAnswer) {
        this.fillAnswer = fillAnswer;
    }

    public List<String> getJudgeAnswer() {
        return judgeAnswer;
    }

    public void setJudgeAnswer(List<String> judgeAnswer) {
        this.judgeAnswer = judgeAnswer;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String toString() {
        return "SurveyRs{" +
                "examCode=" + examCode +
                ", studentEmail='" + studentEmail + '\'' +
                ", topic1Answer=" + topic1Answer +
                ", fillAnswer=" + fillAnswer +
                ", judgeAnswer=" + judgeAnswer +
                ", answerDate='" + answerDate + '\'' +
                '}';
    }
}
