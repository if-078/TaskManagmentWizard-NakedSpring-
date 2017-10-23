package com.softserve.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String getIndexPage() {
        return "old";
    }
    @RequestMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
