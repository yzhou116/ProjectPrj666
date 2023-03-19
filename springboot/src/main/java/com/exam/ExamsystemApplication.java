package com.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//Code Comment:Spring boot main starter
@SpringBootApplication()
public class ExamsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamsystemApplication.class, args);
    }

}

