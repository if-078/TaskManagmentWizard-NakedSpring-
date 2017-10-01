package com.softserve.if078.tmwSpring.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.softserve.if078.tmwSpring.entities.User;
import com.softserve.if078.tmwSpring.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	List<User> getAllUsers() throws SQLException {
		return userService.getAll();
	}

	@PostMapping("/")
	User createUser(@RequestBody User user)throws SQLException {
		return userService.create(user);
	}

	@GetMapping("/{userid}")
	User getUser(@PathVariable Integer userid)throws SQLException {
		return userService.get(userid);
	}

	@PutMapping("/")
	boolean updateUser(@RequestBody User user)throws SQLException {
		return userService.update(user);
	}

	@DeleteMapping("/{userid}")
	boolean deleteUser(@PathVariable Integer userid) throws SQLException {
		return userService.delete(userid);
	}

}
