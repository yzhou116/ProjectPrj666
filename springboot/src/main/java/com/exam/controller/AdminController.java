package com.exam.controller;

import com.exam.entity.Admin;
import com.exam.entity.ApiResult;
import com.exam.serviceimpl.AdminServiceImpl;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//Code Comment: This is the controller class, we get the call from frontend side and pass the data to service side.
@RestController
@CrossOrigin
public class AdminController {

    private AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }

    @GetMapping("/admins")
    public ApiResult findAll(){
        System.out.println("Find all");
        return ApiResultHandler.success(adminService.findAll());
    }

    @GetMapping("/admin/{adminId}")
    public ApiResult findById(@PathVariable("adminId") Integer adminId){
        System.out.println("Find by id");
        return ApiResultHandler.success(adminService.findById(adminId));
    }

    @DeleteMapping("/admin/{adminId}")
    public ApiResult deleteById(@PathVariable("adminId") Integer adminId){
        adminService.deleteById(adminId);
        return ApiResultHandler.success();
    }

    @PutMapping("/admin/{adminId}")
    public ApiResult update(@PathVariable("adminId") Integer adminId, Admin admin){
        return ApiResultHandler.success(adminService.update(admin));
    }

    @PostMapping("/admin")
    public ApiResult add(Admin admin){
        return ApiResultHandler.success(adminService.add(admin));
    }
}
