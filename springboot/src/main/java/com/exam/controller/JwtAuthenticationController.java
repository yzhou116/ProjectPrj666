package com.exam.controller;

import com.exam.entity.*;
import com.exam.serviceimpl.JwtUserDetailsService;
import com.exam.serviceimpl.LoginServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private LoginServiceImpl loginService;


    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ApiResult createAuthenticationToken(@RequestBody Login login) throws Exception {

      /*  authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

       */
        String username = login.getUsername();
        String password = login.getPassword();

        if(login.getType().equals("Admin")){
           // Admin adminRes = loginService.adminLogin(username, password);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            password = bCryptPasswordEncoder.encode(password);
            int adminRes = loginService.updateadminLogin(username,password);
            if (adminRes == 1) {
                return ApiResultHandler.buildApiResult(200, "Request Success", adminRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Publisher")){
         //   Teacher teacherRes = loginService.teacherLogin(username,password);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            password = bCryptPasswordEncoder.encode(password);
            int teacherRes = loginService.updateTeacherLogin(username,password);
            if (teacherRes == 1) {
                return ApiResultHandler.buildApiResult(200, "Request Success", teacherRes);
            } else {
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Subscriber")){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            password = bCryptPasswordEncoder.encode(password);
            int studentRes = loginService.updatestudentLogin(username,password);
         //   int studentRes = 1;
            if (studentRes == 1) {
                return ApiResultHandler.buildApiResult(200, "Request Success", studentRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }
        return ApiResultHandler.buildApiResult(400, "Request Fail", null);

    }
    @RequestMapping(value = "/authenticatet", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(authenticationRequest.getType().equals("Admin")){
            Admin admin = loginService.getAdmin(authenticationRequest.getUsername());
            //   authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            if(admin!= null){
                boolean checker = bCryptPasswordEncoder.matches( authenticationRequest.getPassword(),admin.getPwd());
                if(checker){
                    final UserDetails userDetails = userDetailsService
                            .loadUserByUsername(authenticationRequest.getUsername());

                    final String token = jwtTokenUtil.generateToken(userDetails);

                    return ResponseEntity.ok(new JwtResponse(token));
                }else{
                    return  new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
                }
            }else{
                return  new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }


        }else if(authenticationRequest.getType().equals("Publisher")){
            Teacher teacher = loginService.getTeacher(authenticationRequest.getUsername());
            //   authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            if(teacher != null){
                boolean checker = bCryptPasswordEncoder.matches( authenticationRequest.getPassword(),teacher.getPwd());
                if(checker){
                    final UserDetails userDetails = userDetailsService
                            .loadUserByUsername(authenticationRequest.getUsername());

                    final String token = jwtTokenUtil.generateToken(userDetails);

                    return ResponseEntity.ok(new JwtResponse(token));
                }else{
                    return  new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
                }
            }else {
                return  new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }


        }else if(authenticationRequest.getType().equals("Subscriber")){
            Student student = loginService.getStudent(authenticationRequest.getUsername());
            //   authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            if(student != null){
                boolean checker = bCryptPasswordEncoder.matches( authenticationRequest.getPassword(),student.getPwd());
                if(checker){
                    final UserDetails userDetails = userDetailsService
                            .loadUserByUsername(authenticationRequest.getUsername());

                    final String token = jwtTokenUtil.generateToken(userDetails);

                    return ResponseEntity.ok(new JwtResponse(token));
                }else{
                    return     new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
                }
            }else {
                return  new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }


        }

        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

    }

    private void authenticate(String username, String password) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        int i = 0;
        try {

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}