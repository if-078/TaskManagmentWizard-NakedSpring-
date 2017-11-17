package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.Role;
import com.softserve.academy.tmw.service.api.RoleServiceInterface;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
public class RoleController {

  @Autowired
  public RoleServiceInterface roleService;

  @GetMapping("/")
  List<Role> getAllRoles() throws SQLException {
    return roleService.getAll();
  }

  @PostMapping("/")
  Role createRole(@RequestBody Role role) throws SQLException {
    return roleService.create(role);
  }

  @GetMapping("/{id}")
  Role getRole(@PathVariable Integer id) throws SQLException {
    return roleService.findOne(id);
  }

  @PutMapping("/")
  @ResponseStatus(HttpStatus.OK)
  boolean updateRole(@RequestBody Role role) throws SQLException {
    return roleService.update(role);
  }

  @DeleteMapping("/{id}")
  boolean deleteRole(@PathVariable Integer id) throws SQLException {
    return roleService.delete(id);
  }
}
