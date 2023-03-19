package com.exam.serviceimpl;

import com.exam.entity.Admin;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.mapper.LoginMapper;
import com.exam.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Admin adminLogin(String username, String password) {
        return loginMapper.adminLogin(username,password);
    }

    @Override
    public Teacher teacherLogin(String username, String password) {
        return loginMapper.teacherLogin(username,password);
    }

    @Override
    public Student studentLogin(String username, String password) {
        return loginMapper.studentLogin(username,password);
    }

    @Override
    public Student getStudent(String username) {
        return loginMapper.getStudentById(username);
    }

    @Override
    public Teacher getTeacher(String username) {
        return loginMapper.getTeacherById(username);
    }

    @Override
    public Admin getAdmin(String username) {
        return loginMapper.getAdminById(username);
    }

    @Override
    public int updatestudentLogin(String username, String password) {
        return loginMapper.updatestudentLogin(username,password);
    }

    @Override
    public int updateTeacherLogin(String username, String password) {
        return loginMapper.updateTeacherLogin(username,password);
    }

    @Override
    public int updateadminLogin(String username, String password) {
        return loginMapper.updateAdminLogin(username,password);
    }
}
