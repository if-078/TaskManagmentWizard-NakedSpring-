package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

  @Autowired
  UserServiceInterface userService;

  @GetMapping("/team/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  List<User> getAllUsers(@PathVariable Integer taskId, @RequestParam(name = "userId", required = false) int userId) throws SQLException {
    return userService.getTeamByTask(taskId, userId);
  }

  @PostMapping("/add/")
  @ResponseStatus(HttpStatus.CREATED)
  User createUser(@RequestBody User user) throws SQLException {
    return userService.create(user);
  }

  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  User getUser(@PathVariable Integer id) throws SQLException {
    return userService.findOne(id);
  }

  @GetMapping("/email/{email}")
  @ResponseStatus(HttpStatus.OK)
  User getUser(@PathVariable String email) throws SQLException {
    return userService.findByEmail(email);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean updateUser(@RequestBody User user) throws SQLException {
    return userService.update(user);
  }

  @DeleteMapping("/del/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean deleteUser(@PathVariable Integer id) throws SQLException {
    return userService.delete(id);
  }

  @GetMapping("/verify/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  boolean verifyUser (@PathVariable Integer id,  @RequestParam(name = "key") long userKey){
    return userService.verify(id, userKey);
  }
}