package com.exam.controller;

import com.exam.entity.*;
import com.exam.service.StudentService;
import com.exam.service.TeacherService;
import com.exam.serviceimpl.JwtUserDetailsService;
import com.exam.serviceimpl.LoginServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login) {

        String username = login.getUsername();
        String password = login.getPassword();
        if(login.getType().equals("Admin")){
            Admin adminRes = loginService.adminLogin(username, password);
            if (adminRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", adminRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Publisher")){
            Teacher teacherRes = loginService.teacherLogin(username,password);
            if (teacherRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", teacherRes);
            } else {
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Subscriber")){
            Student studentRes = loginService.studentLogin(username,password);
            if (studentRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", studentRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }
        return ApiResultHandler.buildApiResult(400, "Request Fail", null);
    }
    @PostMapping("/updatelogin")
    public ApiResult loginupdate(@RequestBody Login login){
        String username = login.getUsername();
        String password = login.getPassword();

        if(login.getType().equals("Admin")){
            Admin adminRes = loginService.adminLogin(username, password);
            if (adminRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", adminRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Publisher")){
            Teacher teacherRes = loginService.teacherLogin(username,password);
            if (teacherRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", teacherRes);
            } else {
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Subscriber")){
            //   BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       //     password = bCryptPasswordEncoder.encode(password);
            int studentRes = loginService.updatestudentLogin(username,password);
            if (studentRes == 1) {
                return ApiResultHandler.buildApiResult(200, "Request Success", studentRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }
        return ApiResultHandler.buildApiResult(400, "Request Fail", null);
    }

    @PostMapping("/register")
    public ApiResult registerUser(@RequestBody RegisterForm registerForm){
        if(registerForm.getPassword().equals(registerForm.getPasswordConfirm())){
            String pass = bCryptPasswordEncoder.encode(registerForm.getPassword());
            Student student = new Student();

            student.setStudentUsername(registerForm.getUsername());
            student.setStudentName(registerForm.getName());
            student.setGrade("2023");
            student.setMajor("Computer Science");
            student.setClazz("-1");
            student.setInstitute("Seneca");
            student.setTel("911");
            student.setEmail(registerForm.getUsername());
            student.setPwd(pass);
            student.setCardId("119");
            student.setSex("M");
            student.setRole("2");
            studentService.add(student);
            Teacher teacher = new Teacher();
            teacher.setTeacherUsername(registerForm.getUsername());
            teacher.setEmail(registerForm.getUsername());
            teacher.setTeacherName(registerForm.getName());
            teacher.setInstitute("Seneca");
            teacher.setTel("911");
            teacher.setEmail(registerForm.getUsername());
            teacher.setPwd(pass);
            teacher.setCardId("129");
            teacher.setType("Teacher");
            teacher.setRole("1");
            teacherService.add(teacher);
            return ApiResultHandler.buildApiResult(200, "Request Success", null);


        }

        System.out.println(registerForm.getPassword());


        return ApiResultHandler.buildApiResult(400, "Request Fail", null);
    }



}
