package com.exam.serviceimpl;

import com.exam.entity.Admin;
import com.exam.mapper.AdminMapper;
import com.exam.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//Code Comment:This is the service part of the project, it plays rule like middle service. The controller pass the data to here, and this one will call the DAO class to do the CRUD of database.
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public Admin findById(Integer adminId) {
        return adminMapper.findById(adminId);
    }

    @Override
    public int deleteById(int adminId) {
        return adminMapper.deleteById(adminId);
    }

    @Override
    public int update(Admin admin) {
        return adminMapper.update(admin);
    }

    @Override
    public int add(Admin admin) {
        return 0;
    }
}
