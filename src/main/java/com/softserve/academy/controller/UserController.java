package com.softserve.academy.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.ServiceInterface;
import com.softserve.academy.service.interfaces.UserService;

@RestController
@RequestMapping("users")
public class UserController {
  
  @Autowired
  UserService userService;
  
	@GetMapping("/")
	List<User> getAllUsers() throws SQLException {
		return userService.getAll();
	}

	@PostMapping("/")
	User createUser( @Validated @RequestBody User user)throws SQLException {
		return userService.create(user);
	}

	@GetMapping("/{id}")
	User getUser(@PathVariable Integer id)throws SQLException {
		return userService.findOne(id);
	}
	
	@GetMapping("/email/{email}")
	User getUser(@PathVariable String email)throws SQLException {
		return userService.findByEmail(email);
	}

	@PutMapping("/")
	boolean updateUser(@Validated @RequestBody User user)throws SQLException {
		return userService.update(user);
	}

	@DeleteMapping("/{id}")
	boolean deleteUser(@PathVariable Integer id) throws SQLException {
		return userService.delete(id);
	}

}
