package com.softserve.academy.dao.implementation;

import com.softserve.academy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> {
  @Autowired
  public UserDaoImpl(String userTable, RowMapper<User> userMapper) {
    super(userTable, userMapper);
  }

  @Override
  public User create(User entity) {
    String sql = "INSERT INTO " + table + " (name, pass, email) VALUES (:name, :pass, :email)";
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    param.addValue("name", entity.getName());
    param.addValue("pass", entity.getPass());
    param.addValue("email", entity.getEmail());
    operations.update(sql, param, keyHolder);
    entity.setId((int) keyHolder.getKey());
    return entity;
  }

  @Override
  public boolean update(User entity) {
    return false;
  }

}
