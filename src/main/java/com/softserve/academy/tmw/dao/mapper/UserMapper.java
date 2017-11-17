package com.softserve.academy.tmw.dao.mapper;

import com.softserve.academy.tmw.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

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

