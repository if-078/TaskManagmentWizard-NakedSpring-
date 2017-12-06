package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

  @Autowired
  UserServiceInterface userService;

  @GetMapping("/team/{taskId}")
  @ResponseStatus(HttpStatus.OK)
  List<User> getTeam(@PathVariable Integer taskId,
      @RequestParam(name = "userId", required = false) int userId) throws SQLException {
    return userService.getTeamByTask(taskId, userId);
  }

  @GetMapping("/all")
  @ResponseStatus(HttpStatus.OK)
  List<User> getAll() throws SQLException {
    return userService.getAll();
  }

  @PostMapping("/add/")
  @ResponseStatus(HttpStatus.CREATED)
  User createUser(@RequestBody User user) {
    return userService.create(user);
  }

  @GetMapping("/id/{id}")
  @ResponseStatus(HttpStatus.OK)
  User get(@PathVariable Integer id) {
    return userService.findOne(id);
  }

  @GetMapping("/email/{email}")
  @ResponseStatus(HttpStatus.OK)
  User get(@PathVariable String email) {
    return userService.findByEmail(email);
  }

  @PutMapping("/update")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean update(@RequestBody User user) {
    return userService.update(user);
  }

  @DeleteMapping("/del/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean delete(@PathVariable Integer id) {
    return userService.delete(id);
  }


}