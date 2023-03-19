package com.exam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Teacher;

import java.util.List;

public interface TeacherService {

    IPage<Teacher> findAll(Page<Teacher> page);

    public List<Teacher> findAll();

    public Teacher findById(Integer teacherId);

    public int deleteByUsrName(String teacherId);

    public int update(String tname, String temail, String tsex, String tins,String tpwd, String card, String tel, String type,  String oringalEmail);

    public int add(Teacher teacher);
}
