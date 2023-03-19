package com.exam.serviceimpl;

import java.util.ArrayList;

import com.exam.entity.Admin;
import com.exam.entity.Student;
import com.exam.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private LoginServiceImpl loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = loginService.getStudent(username);
        if(student!=null){
            return new User(username, student.getPwd(),
                    new ArrayList<>());
        }
        Teacher teacher = loginService.getTeacher(username);
        if(teacher != null){
            return new User(username, teacher.getPwd(),
                    new ArrayList<>());
        }
        Admin admin = loginService.getAdmin(username);
        if(admin != null){
            return new User(username, admin.getPwd(),
                    new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        /*
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

         */

    }
}