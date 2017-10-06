package com.softserve.academy.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.UserService;

@RestController
@RequestMapping("users")
public class UserController {
  
  @Autowired
  UserService userService;
  
	@GetMapping("/all")
	List<User> getAllUsers() throws SQLException {
		return userService.getAll();
	}

	@PostMapping("/add/")
	@ResponseStatus(HttpStatus.CREATED)
	User createUser(@RequestBody User user)throws SQLException {
		return userService.create(user);
	}

	@GetMapping("/id/{id}")
	User getUser(@PathVariable Integer id)throws SQLException {
		return userService.findOne(id);
	}
	
	@GetMapping("/email/{email}")
	User getUser(@PathVariable String email)throws SQLException {
		return userService.findByEmail(email);
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	boolean updateUser(@RequestBody User user)throws SQLException {
		return userService.update(user);
	}

	@DeleteMapping("/del/{id}")
	boolean deleteUser(@PathVariable Integer id) throws SQLException {
		return userService.delete(id);
	}

}
