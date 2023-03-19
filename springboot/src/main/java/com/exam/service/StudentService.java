package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Student;

import java.util.List;

public interface StudentService {

    IPage<Student> findAll(Page<Student> page);

    Student findById(Integer studentId);

    int deleteById(Integer studentId);

    int deleteByUsername(String username);

    int update(Student student);

    int updatePwd(Student student);
    int add(Student student);

    int updateStudent(String sname,String semail,String ssex, String ins, String spwd, String card, String tel,String oringalEmail);
}
