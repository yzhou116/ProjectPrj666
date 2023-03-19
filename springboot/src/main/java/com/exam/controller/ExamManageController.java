package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.ExamManage;
import com.exam.serviceimpl.ExamManageServiceImpl;
import com.exam.util.ApiResultHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ExamManageController {

    @Autowired
    private ExamManageServiceImpl examManageService;

    @GetMapping("/exams")
    public ApiResult findAll(){
        System.out.println("不分页查询所有试卷");
        ApiResult apiResult;

        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", examManageService.findAll());
        return apiResult;
    }

    @PostMapping("/examssss")
    public ApiResult theToken(){

        ApiResult apiResult;
        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", examManageService.findAll());
        return apiResult;
    }
    /*
    @GetMapping("/teacherexams/{page}/{size}/{username}")
    public ApiResult findExamsForTeacher(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("username") String username){
        ApiResult apiResult;

        List<ExamManage> all = examManageService.findExamByTeacherName(username);
        Page<ExamManage> examManage1 = new Page<>();
        int start = 0;
        if(page > 1){
            start = (page -1) * size;
        }
        List<ExamManage> tempArr = new ArrayList<>();
        for(int i = 0; i < size && start <= all.size() -1; i++){
            tempArr.add(all.get(start));
            start++;
        }

        examManage1.setTotal(all.size());
        examManage1.setRecords(tempArr);
        examManage1.setPages(page);
        examManage1.setSize(size);

        apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", examManage1);
        return apiResult;
    };
*/



    @GetMapping("/exams/{page}/{size}/{username}")
    public ApiResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("username") String username){
        System.out.println("分页查询所有试卷");
        ApiResult apiResult;

       // Page<ExamManage> examManage = new Page<>(page,size);
      //  IPage<ExamManage> all = examManageService.findAll(examManage);
        List<ExamManage> all = examManageService.findExamByUserNameWithIsPublic(username);
        Page<ExamManage> examManage1 = new Page<>();
        int start = 0;
        if(page > 1){
            start = (page -1) * size;
        }
        List<ExamManage> tempArr = new ArrayList<>();
        for(int i = 0; i < size && start <= all.size() -1; i++){
            tempArr.add(all.get(start));
            start++;
        }

        examManage1.setTotal(all.size());
        examManage1.setRecords(tempArr);
        examManage1.setPages(page);
        examManage1.setSize(size);
        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", examManage1);
        return apiResult;
    }

    @GetMapping("/teacherexams/{page}/{size}/{username}")
    public ApiResult teacherfindAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("username") String username){
        System.out.println("分页查询所有试卷");
        ApiResult apiResult;

        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.teacherfindAll(examManage,username);
        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", all);
        return apiResult;
    }


    @GetMapping("/teacherSuveryexams/{page}/{size}/{username}")
    public ApiResult teacherSuveryfindAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("username") String username){
        System.out.println("分页查询所有试卷");
        ApiResult apiResult;

        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.teacherfindAll(examManage,username);
        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", all);
        return apiResult;
    }


    @GetMapping("/teacherSuveryexamsAll/{username}")
    public ApiResult teacherSuveryexamsAll(@PathVariable("username") String username){
        ApiResult apiResult;

       // Page<ExamManage> examManage = new Page<>(page,size);
       // IPage<ExamManage> all = examManageService.teacherfindAll(examManage,username);
        List<ExamManage> list = examManageService.findExamByTeacherName(username);
        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", list);
        return apiResult;
    }








    @GetMapping("/userexams/{page}/{size}/{userCode}")
    public ApiResult findUserAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("userCode") String userCode){

        ApiResult apiResult;
        System.out.println("This is usercode"+ userCode);
        List<ExamManage> examManage = new ArrayList<>();

        List<ExamManage> all = examManageService.findExamByUserName(examManage,userCode);
      //  Page<ExamManage> examManage1 = new Page<>(page,size);
        Page<ExamManage> examManage1 = new Page<>();
        int start = 0;
        if(page > 1){
            start = (page -1) * size;
        }
        List<ExamManage> tempArr = new ArrayList<>();
        for(int i = 0; i < size && start <= all.size() -1; i++){
            tempArr.add(all.get(start));
            start++;
        }

        examManage1.setTotal(all.size());
        examManage1.setRecords(tempArr);
        examManage1.setPages(page);
        examManage1.setSize(size);
      //  apiResult = ApiResultHandler.buildApiResult(200, "请求成功！", all);
        apiResult = ApiResultHandler.buildApiResult(200, "Request Success", examManage1);
        return apiResult;
    }

    @GetMapping("/exam/{examCode}/{userCode}")
    public ApiResult findById(@PathVariable("examCode") Integer examCode, @PathVariable("userCode") String userCode){

        System.out.println("This is usercode"+ userCode);
        ExamManage res = examManageService.findById(examCode);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"Can not find exam",null);
        }
        return ApiResultHandler.buildApiResult(200,"Request Success",res);
    }

    @DeleteMapping("/exam/{examCode}")
    public ApiResult deleteById(@PathVariable("examCode") Integer examCode){
        int res = examManageService.delete(examCode);
        return ApiResultHandler.buildApiResult(200,"Request Success",res);
    }

    @PutMapping("/exam")
    public ApiResult update(@RequestBody ExamManage exammanage){
        int res = examManageService.update(exammanage);
//        if (res == 0) {
//            return ApiResultHandler.buildApiResult(20000,"请求参数错误");
//        }
        System.out.print("更新操作执行---");
        return ApiResultHandler.buildApiResult(200,"Request Success",res);
    }

    @PostMapping("/exam")
    public ApiResult add(@RequestBody ExamManage exammanage){
        if(exammanage.getTotalTime() <= 0){
            exammanage.setTotalTime(-1);
        }
        int res = examManageService.add(exammanage);

        if (res ==1) {
            return ApiResultHandler.buildApiResult(200, "Request Success", res);
        } else {
            return  ApiResultHandler.buildApiResult(400,"Request Fail",res);
        }
    }

    @GetMapping("/examManagePaperId")
    public ApiResult findOnlyPaperId() {
        ExamManage res = examManageService.findOnlyPaperId();
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"Request Success",res);
        }
        return ApiResultHandler.buildApiResult(400,"Request Fail",res);
    }


    @GetMapping("/updateExamstudent/{username}/{examCode}")
    public ApiResult addStudentToExam(@PathVariable("username") String username,@PathVariable("examCode") String examCode) throws JsonProcessingException {
        int res = examManageService.addStudent(examCode,username);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"Request Success",res);
        }
        return ApiResultHandler.buildApiResult(400,"Request Fail",res);
    }
//
    //
    @GetMapping("/anonymousExam/{examCode}/{userCode}")
    public ApiResult getSurveyForAnonymous(@PathVariable("userCode") String username,@PathVariable("examCode") Integer examCode) throws JsonProcessingException {

        System.out.println("This is usercode"+ username);
        ExamManage res = examManageService.findById(examCode);
        if(res == null) {
            return ApiResultHandler.buildApiResult(10000,"Request Fail",null);
        }
        return ApiResultHandler.buildApiResult(200,"Request Success",res);


    }

}
