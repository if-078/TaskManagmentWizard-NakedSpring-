package com.softserve.academy.tmw.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  static Logger logger = Logger.getLogger(MainController.class.getName());

  @RequestMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @RequestMapping("/")
  public String getIndexPage(HttpServletRequest httpServletRequest) {
    String userIp = httpServletRequest.getRemoteAddr();
    logger.info("User with ip: " + userIp + " connected");
    return "index";
  }
}