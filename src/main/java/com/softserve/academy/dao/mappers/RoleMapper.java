/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

  @Override
  public Role mapRow(ResultSet resultSet, int i) throws SQLException {
    Role role = new Role(resultSet.getInt("id"), resultSet.getString("name"));
    return role;
  }

}
