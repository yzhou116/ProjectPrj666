package com.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.ApiResult;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.service.StudentService;
import com.exam.serviceimpl.TeacherServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.vo.AnswerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TeacherController {

    private TeacherServiceImpl teacherService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private StudentService studentService;
    @Autowired
    public TeacherController(TeacherServiceImpl teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size){
        Page<Teacher> teacherPage = new Page<>(page,size);
        IPage<Teacher> teacherIPage = teacherService.findAll(teacherPage);

        return ApiResultHandler.buildApiResult(200,"Query Teacher",teacherIPage);
    }

    @GetMapping("/teacher/{teacherId}")
    public ApiResult findById(@PathVariable("teacherId") Integer teacherId){
        return ApiResultHandler.success(teacherService.findById(teacherId));
    }

    @DeleteMapping("/teacher/{username}")
    public ApiResult deleteById(@PathVariable("username") String username){
        studentService.deleteByUsername(username);
        return ApiResultHandler.success(teacherService.deleteByUsrName(username));
    }

    @PutMapping("/teacher/{oringalEmail}")
    public ApiResult update(@RequestBody Teacher teacher, @PathVariable String oringalEmail){

        teacher.setPwd(bCryptPasswordEncoder.encode(teacher.getPwd()));
        teacher.setTeacherUsername(oringalEmail);
        Student student = new Student();
      //  student.setStudentUsername(teacher.getEmail());
        student.setStudentUsername(teacher.getTeacherUsername());
        student.setStudentName(teacher.getTeacherName());
        student.setStudentName(teacher.getTeacherName());
        student.setGrade("2023");
        student.setMajor("Computer Science");
        student.setClazz("-1");
        student.setInstitute(teacher.getInstitute());
        student.setTel(teacher.getTel());
        student.setEmail(teacher.getEmail());
        student.setPwd(teacher.getPwd());
        student.setCardId(teacher.getCardId());
        student.setSex(teacher.getSex());
        student.setRole("2");
        studentService.updateStudent(student.getStudentName(),student.getEmail(),student.getSex(),student.getInstitute(),teacher.getPwd(),student.getCardId(),student.getTel(),oringalEmail);
        return ApiResultHandler.success(teacherService.update(teacher.getTeacherName(),teacher.getEmail(),teacher.getSex(),teacher.getInstitute(),teacher.getPwd(),teacher.getCardId(),teacher.getTel(),teacher.getType(),oringalEmail));
    }

    @PostMapping("/teacher")
    public ApiResult add(@RequestBody Teacher teacher){
        teacher.setPwd(bCryptPasswordEncoder.encode(teacher.getPwd()));
        teacher.setTeacherUsername(teacher.getEmail());
        Student student = new Student();
        student.setStudentUsername(teacher.getEmail());
        student.setStudentUsername(teacher.getTeacherUsername());
        student.setStudentName(teacher.getTeacherName());
        student.setGrade("2023");
        student.setMajor("Computer Science");
        student.setClazz("-1");
        student.setInstitute(teacher.getInstitute());
        student.setTel(teacher.getTel());
        student.setEmail(teacher.getTeacherUsername());
        student.setPwd(teacher.getPwd());
        student.setCardId(teacher.getCardId());
        student.setSex(teacher.getSex());
        student.setRole("2");
        studentService.add(student);
        return ApiResultHandler.success(teacherService.add(teacher) );
    }
}
