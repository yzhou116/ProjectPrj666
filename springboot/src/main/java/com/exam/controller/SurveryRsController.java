package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.Student;
import com.exam.entity.SurveyRs;
import com.exam.service.SurveyRsService;
import com.exam.serviceimpl.SurveyRsImpl;
import com.exam.util.ApiResultHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class SurveryRsController {

    @Autowired
    private SurveyRsService surveyRsService;

    @PostMapping("/surveyRs")
    public ApiResult insertSurveyRes(@RequestBody SurveyRs surveyRs) throws IOException {

        System.out.println(surveyRs.toString());

        int res = surveyRsService.insertSurveyRs(surveyRs.getExamCode(),surveyRs.getStudentEmail(),surveyRs.getTopic1Answer(),surveyRs.getFillAnswer(),surveyRs.getJudgeAnswer(), surveyRs.getMultiQuestionList(),surveyRs.getFillQuestionList(),surveyRs.getJudgeQuestionsList(),surveyRs.getAnswerDate());


        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", res);
        return apiResult;
    }
}
