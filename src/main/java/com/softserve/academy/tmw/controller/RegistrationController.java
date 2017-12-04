package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/register")
public class RegistrationController {

  private UserService userService;

  @Autowired
  public RegistrationController(UserService userService) {
    this.userService = userService;
  }


    @PostMapping
    public ResponseEntity register(@RequestBody User user) {
         User userByEmail=userService.findByEmail(user.getEmail());
        if ( userByEmail== null) {
            userService.create(user);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/verify/{key}") //метод крейт при створенні юзера посилає лінку з айдішкою юзера і згенереним ключем, та лінка має тригерети цей контроллер
    @ResponseStatus(HttpStatus.CREATED)
    boolean verifyUser (@PathVariable Long key, HttpServletRequest request, HttpServletResponse response){

        try {
            response.sendRedirect("http://localhost:8585/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userService.verify(key);
    }

}