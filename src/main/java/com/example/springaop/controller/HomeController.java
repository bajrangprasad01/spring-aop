package com.example.springaop.controller;

import com.example.springaop.service.Business1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    @Autowired
    private Business1 business1;

    @GetMapping ("/hello")
    public String hello(@RequestParam (defaultValue = "Welcome back") String name, HttpServletRequest request) {
        return "Welcome to Hello API " + name;
    }

    @GetMapping ("/call")
    public String callService() {
        return business1.getBusiness();
    }
}
