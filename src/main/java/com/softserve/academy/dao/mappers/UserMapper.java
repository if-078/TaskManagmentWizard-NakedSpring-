/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Oleg
 */
public class UserMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int i) throws SQLException {
    User u = new User();
    u.setId(rs.getInt("id"));
    u.setName(rs.getString("name"));
    u.setPass(rs.getString("pass"));
    u.setEmail(rs.getString("email"));
    return u;
  }

}
