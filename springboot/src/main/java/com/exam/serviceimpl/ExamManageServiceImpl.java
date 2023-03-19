package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ExamManage;
import com.exam.mapper.ExamManageMapper;
import com.exam.service.ExamManageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExamManageServiceImpl implements ExamManageService {
    @Autowired
    private ExamManageMapper examManageMapper;


    @Override
    public List<ExamManage> findAll() {
        return examManageMapper.findAll();
    }

    @Override
    public IPage<ExamManage> findAll(Page<ExamManage> page) {
        return examManageMapper.findAll(page);
    }

    @Override
    public ExamManage findById(Integer examCode) {
        return examManageMapper.findById(examCode);
    }

    @Override
    public int delete(Integer examCode) {
        return examManageMapper.delete(examCode);
    }

    @Override
    public int update(ExamManage exammanage) {
        return examManageMapper.update(exammanage);
    }

    @Override
    public int add(ExamManage exammanage) {
        return examManageMapper.add(exammanage);
    }

    @Override
    public ExamManage findOnlyPaperId() {
        return examManageMapper.findOnlyPaperId();
    }

    @Override
    public IPage<ExamManage> teacherfindAll(Page<ExamManage> page, String username) {
        IPage<ExamManage> mpage = examManageMapper.teacherfindAll(page,username);
        return examManageMapper.teacherfindAll(page,username);
    }

    @Override
    public List<ExamManage> findExamByTeacherName(String username) {
        List<ExamManage> list = examManageMapper.findSurveyByTeacherId(username);
        return list;
    }

    @Override
    public int addStudent(String examCode, String username) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String stuLst = examManageMapper.findStudentInExam(examCode);
        ArrayList<String> list;
        if(stuLst==null){
            list = new ArrayList<>();
            list.add(username);
        //    String stuList = mapper.writeValueAsString(list);
            String stuList = list.get(0) + ",";
            return examManageMapper.addStudenToExam(stuList,examCode);
        }else{
            String[] temp = stuLst.split(",");
            for (int i = 0; i < temp.length; i++) {
                temp[i] = temp[i] + ",";
            }
            list = new ArrayList<>(Arrays.asList(temp));
            list.add(username + ",");
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                str.append(list.get(i));
            }

            return examManageMapper.addStudenToExam(str.toString(),examCode);
        }
    }

    @Override
    public List<ExamManage> findExamByUserName(List<ExamManage> examManage, String userName) {

        List<ExamManage> temp = examManageMapper.findAll();
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getTeacher().equals(userName)){
                examManage.add(temp.get(i));
                continue;
            }
            ExamManage examManage1 = temp.get(i);
            String arr = examManage1.getStudent();
            if(arr != null){
                String[] stuArray = arr.split(",");
                for (int j = 0; j < stuArray.length; j++) {
                    if(stuArray[j].equals(userName)){
                        examManage.add(examManage1);
                        break;
                    }
                }
            }

        }

        return examManage;
    }

    @Override
    public List<ExamManage> findExamByUserNameWithIsPublic(String userCode) {

        List<ExamManage> list = examManageMapper.findByUserNameAndIsPublic(userCode);
        List<ExamManage> examManage = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            ExamManage examManage1 = list.get(i);
            String arr = examManage1.getStudent();
            if(arr != null){
                String[] stuArray = arr.split(",");
                for (int j = 0; j < stuArray.length; j++) {
                    if(stuArray[j].equals(userCode)){
                        break;
                    }
                    if(j == stuArray.length-1){
                        examManage.add(examManage1);
                    }
                }
            }else{
                examManage.add(examManage1);
            }

        }

        return examManage;
    }
}
