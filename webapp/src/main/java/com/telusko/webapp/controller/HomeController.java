package com.telusko.webapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet(){
        return "hi";
    }
    @RequestMapping("/about")
    public String about(){
        return "hi about page";
    }
}
