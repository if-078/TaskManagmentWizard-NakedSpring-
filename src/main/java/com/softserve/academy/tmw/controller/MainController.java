package com.softserve.academy.tmw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String getIndexPage() {
        return "index";
    }
    @RequestMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @RequestMapping("/task")
    public String getTaskPage(){
        return "task";
    }
}
