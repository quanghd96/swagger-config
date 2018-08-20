package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RestController
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostMapping("insert")
    public ResponseEntity<UserLogin> insertUser(@RequestBody HashMap<String, String> body, HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        System.out.println(token);
        return new ResponseEntity<>(new UserLogin("quangdn", "quangdn@arrow-tech.vn", "snncklascnkslncaklsn"), HttpStatus.OK);
    }
}
