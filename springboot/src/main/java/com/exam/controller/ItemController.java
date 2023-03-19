package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.PaperManage;
import com.exam.service.PaperService;
import com.exam.serviceimpl.FillQuestionServiceImpl;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ItemController {

    @Autowired
    MultiQuestionServiceImpl multiQuestionService;

    @Autowired
    FillQuestionServiceImpl fillQuestionService;

    @Autowired
    JudgeQuestionServiceImpl judgeQuestionService;

    @Autowired
    PaperService paperService;



    @PostMapping("/item")
    public ApiResult ItemController(@RequestBody Item item) {
        // 选择题
        Integer changeNumber = item.getChangeNumber();
        // 填空题
        Integer fillNumber = item.getFillNumber();
        // 判断题
        Integer judgeNumber = item.getJudgeNumber();
        //出卷id
        Integer paperId = item.getPaperId();

        // 选择题数据库获取
        List<Integer>  changeNumbers = multiQuestionService.findBySubject(item.getSubject(), changeNumber);
        if(changeNumbers==null){
            return ApiResultHandler.buildApiResult(400,"Muliti Choice Saves Fail",null);
        }
        for (Integer number : changeNumbers) {
            PaperManage paperManage = new PaperManage(paperId,1,number);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"Muliti Choice Saves Fail",null);
        }

        // 填空题
        List<Integer> fills = fillQuestionService.findBySubject(item.getSubject(), fillNumber);
        if(fills==null)
            return ApiResultHandler.buildApiResult(400,"Fill blank Saves Fail",null);
        for (Integer fillNum : fills) {
            PaperManage paperManage = new PaperManage(paperId,2,fillNum);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"Fill blank Saves Fail",null);
        }
        // 判断题
        List<Integer> judges = judgeQuestionService.findBySubject(item.getSubject(), judgeNumber);
        if(fills==null)
            return ApiResultHandler.buildApiResult(400,"True and false Saves Fail",null);
        for (Integer judge : judges) {
            PaperManage paperManage = new PaperManage(paperId,3,judge);
            int index = paperService.add(paperManage);
            if(index==0)
                return ApiResultHandler.buildApiResult(400,"True and false Saves Fail",null);
        }


          return ApiResultHandler.buildApiResult(200,"Request Success",null);
    }
}
