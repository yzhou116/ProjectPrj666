package com.exam.service;

import com.exam.entity.Admin;

import java.util.List;
//Code Comment:Use interface to manage function in service
public interface AdminService{

    public List<Admin> findAll();

    public Admin findById(Integer adminId);

    public int deleteById(int adminId);

    public int update(Admin admin);

    public int add(Admin admin);
}
