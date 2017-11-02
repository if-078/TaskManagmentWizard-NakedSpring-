package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.Role;
import com.softserve.academy.tmw.service.api.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
