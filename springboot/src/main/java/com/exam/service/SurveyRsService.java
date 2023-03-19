package com.exam.service;

import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface SurveyRsService {
     int insertSurveyRs(int examCode, String useremai, List<String> topic1Answer,List<List<String>> fillAnswer,List<String> judgeAnswer,List<MultiQuestion> multiQuestionList,List<FillQuestion> fillQuestionList,List<JudgeQuestion> judgeQuestionsList, String answerDate ) throws IOException;

     List<HashMap<String, String>> findSurveydataByExamCode(Integer examCode) throws IOException;
}
