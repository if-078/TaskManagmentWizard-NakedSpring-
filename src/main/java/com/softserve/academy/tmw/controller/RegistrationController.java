package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.impl.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @GetMapping("/verify/{key}")
  @ResponseStatus(HttpStatus.CREATED)
  void verifyUser(@PathVariable String key, HttpServletResponse response) {
    try {
      boolean b = userService.verify(key);
      if (b) {
        response.sendRedirect("http://localhost:8585");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  @GetMapping("/invitationaccept/{key}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  void acceptInvitation (@PathVariable String key, HttpServletResponse response){
    try {
          if (userService.acceptProjectInvitation(key))
              response.sendRedirect("http://localhost:8585");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}