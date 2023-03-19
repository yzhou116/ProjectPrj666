package com.exam.controller;

import com.exam.entity.*;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import com.exam.serviceimpl.PaperServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PaperController {

    @Autowired
    private PaperServiceImpl paperService;

    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    private FillQuestionServiceImpl fillQuestionService;


    @GetMapping("/papers")
    public ApiResult<PaperManage> findAll() {
       ApiResult res =  ApiResultHandler.buildApiResult(200,"Request Success",paperService.findAll());
       return  res;
    }

    @GetMapping("/paper/{paperId}")
    public Map<Integer, List<?>> findById(@PathVariable("paperId") Integer paperId) {
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);
        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1,multiQuestionRes);
        map.put(2,fillQuestionsRes);
        map.put(3,judgeQuestionRes);
        return  map;
    }

    @PostMapping("/paperManage")
    public ApiResult add(@RequestBody PaperManage paperManage) {
        int res = paperService.add(paperManage);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"Add success",res);
        }
        return ApiResultHandler.buildApiResult(400,"Add Fail",res);
    }

    @GetMapping("/anonymousPaper/{paperId}")
    public Map<Integer, List<?>> findPaperForanonymous(@PathVariable("paperId") Integer paperId) {
        List<MultiQuestion> multiQuestionRes = multiQuestionService.findByIdAndType(paperId);   //选择题题库 1
        List<FillQuestion> fillQuestionsRes = fillQuestionService.findByIdAndType(paperId);     //填空题题库 2
        List<JudgeQuestion> judgeQuestionRes = judgeQuestionService.findByIdAndType(paperId);   //判断题题库 3
        Map<Integer, List<?>> map = new HashMap<>();
        map.put(1,multiQuestionRes);
        map.put(2,fillQuestionsRes);
        map.put(3,judgeQuestionRes);
        return  map;
    }
}
