package com.softserve.academy.controller;

import com.softserve.academy.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import com.softserve.academy.service.interfaces.RoleServiceInterface;

@RestController
@RequestMapping("roles")
public class RoleController {
  
	@Autowired
	public RoleServiceInterface roleService;
  
	@GetMapping("/")
	List<Role> getAllRoles() throws SQLException {
		return roleService.getAll();
	}

	@PostMapping("/")
	//@ResponseStatus(HttpStatus.OK)
	Role createRole( @RequestBody Role role)throws SQLException {
		return roleService.create(role);
	}

	@GetMapping("/{id}")
	Role getRole(@PathVariable Integer id)throws SQLException {
		return roleService.findOne(id);
	}
	
	@PutMapping("/")
	@ResponseStatus(HttpStatus.OK)
	boolean updateRole(@RequestBody Role role)throws SQLException {
		return roleService.update(role);
	}

	@DeleteMapping("/{id}")
	boolean deleteRole(@PathVariable Integer id) throws SQLException {
		return roleService.delete(id);
	}
}
