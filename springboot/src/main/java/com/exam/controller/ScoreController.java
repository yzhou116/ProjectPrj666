package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.ExamManage;
import com.exam.entity.Message;
import com.exam.entity.Score;
import com.exam.service.ExamManageService;
import com.exam.service.SurveyRsService;
import com.exam.serviceimpl.ScoreServiceImpl;
import com.exam.util.ApiResultHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @Autowired
    private SurveyRsService surveyRsService;

    @Autowired
    private ExamManageService examManageService;

    @GetMapping("/scores")
    public ApiResult findAll() {
        List<Score> res = scoreService.findAll();
        return ApiResultHandler.buildApiResult(200,"Find Student score",res);
    }
//    分页
    @GetMapping("/score/{page}/{size}/{studentId}")
    public ApiResult findById(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("studentId") String studentId) {
        int stuId = scoreService.findStudentIdbyUsername(studentId);
        Page<Score> scorePage = new Page<>(page, size);
        IPage<Score> res = scoreService.findById(scorePage, stuId);

        return ApiResultHandler.buildApiResult(200, "Search by Id", res);
    }

//    不分页
    @GetMapping("/score/{studentId}")
        public ApiResult findById(@PathVariable("studentId") String studentId) {
        int stuId = scoreService.findStudentIdbyUsername(studentId);
        List<Score> res = scoreService.findById(stuId);
        if (!res.isEmpty()) {
            return ApiResultHandler.buildApiResult(200, "search by Id", res);
        } else {
            return ApiResultHandler.buildApiResult(400, "Id does not exisit", res);
        }
    }

    @PostMapping("/score")
    public ApiResult add(@RequestBody Score score) {
        int stuId = scoreService.findStudentIdbyUsername(score.getStudentId());
        int res = scoreService.add(score.getExamCode(), stuId,score.getSubject(), score.getPtScore(), score.getEtScore(), score.getScore(), score.getAnswerDate(),score.getScoreId());
        if (res == 0) {
            return ApiResultHandler.buildApiResult(400,"Add Fail",res);
        }else {
            ExamManage examManage = examManageService.findById(score.getExamCode());
            return ApiResultHandler.buildApiResult(200,"Add Success",examManage);
        }
    }

    @GetMapping("/scores/{examCode}")
    public ApiResult findByExamCode(@PathVariable("examCode") Integer examCode) {
        List<Score> scores = scoreService.findScoreForDisplayRes(examCode);
        return ApiResultHandler.buildApiResult(200,"Query Success",scores);
    }
    @GetMapping("/surveyRes/{examCode}")
    public ApiResult getSurveyRes(@PathVariable("examCode") Integer examCode) throws IOException {
        List<HashMap<String, String>> res = surveyRsService.findSurveydataByExamCode(examCode);


      //  String ress = surveyRsMapper.findResByExamCode(20190021);
      //  TypeReference<HashMap<Integer, String>> typeRef
      //          = new TypeReference<HashMap<Integer, String>>() {};
      //  HashMap<Integer, String> dotdotdto = mapper.readValue(ress, typeRef);
        return ApiResultHandler.buildApiResult(200,"Query Success",res);
    }


}
