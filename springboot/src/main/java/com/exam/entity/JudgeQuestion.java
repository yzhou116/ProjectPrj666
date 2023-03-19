package com.exam.entity;

import lombok.Data;

@Data
public class JudgeQuestion {
    private Integer questionId;

    private String subject;

    private String question;

    private String answer;

    private String level;

    private String section;

    private Integer score;

    private String analysis;

    private String teacher;
}