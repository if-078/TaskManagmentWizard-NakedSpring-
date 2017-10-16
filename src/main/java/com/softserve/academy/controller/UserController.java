package com.softserve.academy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.UserServiceInterface;

@RestController
@RequestMapping("users")
public class UserController {
  
  @Autowired
  UserServiceInterface userService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	List<User> getAllUsers() {
		return userService.getAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	User createUser(@Validated @RequestBody User user) {
		return userService.create(user);
	}

	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	User getUser(@PathVariable Integer id) {
		return userService.findOne(id);
	}
	
	@GetMapping("/{email}")
	@ResponseStatus(HttpStatus.OK)
	User getUser(@PathVariable String email) {
		return userService.findByEmail(email);
	}

	@PutMapping("/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	boolean updateUser(@Validated @RequestBody User user) {
		return userService.update(user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	boolean deleteUser(@PathVariable Integer id) {
		return userService.delete(id);
	}
}
