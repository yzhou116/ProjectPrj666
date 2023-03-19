package com.exam.serviceimpl;

import com.exam.entity.FillQuestion;
import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.entity.SurveyReturn;
import com.exam.mapper.SurveyRsMapper;
import com.exam.service.SurveyRsService;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class SurveyRsImpl implements SurveyRsService {
    @Autowired
    private SurveyRsMapper surveyRsMapper;

    @Override
    public int insertSurveyRs(int examCode, String useremail, List<String> topic1Answer, List<List<String>> fillAnswer, List<String> judgeAnswer, List<MultiQuestion> multiQuestionList, List<FillQuestion> fillQuestionList, List<JudgeQuestion> judgeQuestionsList, String answerDate) throws IOException {
        if(topic1Answer.size() == multiQuestionList.size() && fillAnswer.size() == fillQuestionList.size() && judgeAnswer.size() == judgeQuestionsList.size()){
            HashMap<Integer , String> map = new HashMap<>();
            for (int i = 0; i < topic1Answer.size(); i++) {
                String right = "";
                if(topic1Answer.get(i) != null) {
                    switch(topic1Answer.get(i)) {
                        case "1":
                            right = "A";
                            break;
                        case "2":
                            right = "B";
                            break;
                        case "3":
                            right = "C";
                            break;
                        case "4":
                            right = "D";
                    }
                    map.put(multiQuestionList.get(i).getQuestionId(), right);

                }
            }
            for (int i = 0; i <fillAnswer.size() ; i++) {
                map.put(fillQuestionList.get(i).getQuestionId() ,fillAnswer.get(i).get(0));

            }
            for (int i = 0; i < judgeAnswer.size(); i++) {
                String right = "";
                if(judgeAnswer.get(i) != null) {
                    switch(judgeAnswer.get(i)) {
                        case "1":
                            right = "Correct";
                            break;
                        case "2":
                            right = "Wrong";

                    }
                    map.put(judgeQuestionsList.get(i).getQuestionId(), right);

                }

            }
           // 'f1f390fe-982c-4c13-b3b2-0c69813093a7'
            ObjectMapper mapper = new ObjectMapper();


            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

            UUID corrId = UUID.randomUUID();
            String id = corrId.toString();
            int res = surveyRsMapper.add(id,examCode,useremail,jsonResult,answerDate);
            return res;
        }else{
            System.out.println("Something is wrong");
        }
        return 0;
    }

    @Override
    public List<HashMap<String, String>> findSurveydataByExamCode(Integer examCode) throws IOException {

        List<SurveyReturn> list = surveyRsMapper.findSurveyByExamCode(examCode);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> lstRes = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
             TypeReference<HashMap<Integer, String>> typeRef = new TypeReference<HashMap<Integer, String>>() {};
             HashMap<Integer, String> map = mapper.readValue(list.get(i).getAnswerResult(), typeRef);
             HashMap<String, String> tempMap = new HashMap<>();
            for (HashMap.Entry<Integer, String> entry : map.entrySet()) {

                if(entry.getValue().equals("A") || entry.getValue().equals("B") ||entry.getValue().equals("C") ||entry.getValue().equals("D") ){
                    MultiQuestion multiQuestion = surveyRsMapper.findMultiQuestion(entry.getKey());
                    tempMap.put(multiQuestion.getQuestion(), entry.getValue());

                }else if(entry.getValue().equals("Correct") || entry.getValue().equals("Wrong")){
                    JudgeQuestion judgeQuestion = surveyRsMapper.findJuedgeQuestion(entry.getKey());
                    tempMap.put(judgeQuestion.getQuestion(), entry.getValue());

                }else{
                    FillQuestion fillQuestion = surveyRsMapper.findFillQuestion(entry.getKey());
                    tempMap.put(fillQuestion.getQuestion(), entry.getValue());

                }
            }
            lstRes.add(tempMap);


            int j = 0;
        }



        return lstRes;
    }
}
