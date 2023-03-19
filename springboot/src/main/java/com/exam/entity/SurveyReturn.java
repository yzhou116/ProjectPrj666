package com.exam.entity;

import lombok.Data;

@Data
public class SurveyReturn {
    private String id;
    private Integer examCode;
    private String studentId;
    private String answerResult;
    private String answerDate;
}
