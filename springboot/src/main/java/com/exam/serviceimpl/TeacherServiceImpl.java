package com.exam.serviceimpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Teacher;
import com.exam.mapper.TeacherMapper;
import com.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public IPage<Teacher> findAll(Page<Teacher> page) {
        return teacherMapper.findAll(page);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.findAll();
    }

    @Override
    public Teacher findById(Integer teacherId) {
        return teacherMapper.findById(teacherId);
    }

    @Override
    public int deleteByUsrName(String teacherId) {
        return teacherMapper.deleteByUsrName(teacherId);
    }

    @Override
    public int update(String tname, String temail, String tsex, String tins, String tpwd, String card, String tel, String type, String oringalEmail) {
        return teacherMapper.update(tname, temail, tsex, tins, tpwd, card, tel, type, oringalEmail);
    }


    @Override
    public int add(Teacher teacher) {
        return teacherMapper.add(teacher);
    }
}
