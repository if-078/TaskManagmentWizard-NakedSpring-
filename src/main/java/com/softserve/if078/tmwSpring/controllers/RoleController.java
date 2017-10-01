package com.softserve.if078.tmwSpring.controllers;

import com.softserve.if078.tmwSpring.entities.Role;
import com.softserve.if078.tmwSpring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/")
	List<Role> getAllRoles() throws SQLException {
		return roleService.getAll();
	}

	@PostMapping("/")
	Role createRole(@RequestBody Role role)throws SQLException {
		return roleService.create(role);
	}

	@PostMapping("/roles")
	List<Role> createRoles(@RequestBody Role... roles)throws SQLException {
		return roleService.create(roles);
	}

	@GetMapping("/{roleid}")
	Role getRole(@PathVariable Integer roleid)throws SQLException {
		return roleService.get(roleid);
	}

	@PutMapping("/")
	boolean updateRole(@RequestBody Role role)throws SQLException {
		return roleService.update(role);
	}

	@DeleteMapping("/{roleid}")
	boolean deleteRole(@PathVariable int roleid) throws SQLException {
		return roleService.delete(roleid);
	}

	@DeleteMapping("/")
	boolean deleteAllRoles() throws SQLException {
		return roleService.deleteAll();
	}

}
