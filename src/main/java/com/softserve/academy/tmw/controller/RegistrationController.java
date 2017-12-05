package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    User userByEmail = userService.findByEmail(user.getEmail());
    if (userByEmail == null) {
      userService.create(user);
      return new ResponseEntity(HttpStatus.CREATED);
    } else {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }
  }

}
