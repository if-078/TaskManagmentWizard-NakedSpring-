package com.softserve.academy.dao.implementation;

import com.softserve.academy.entity.User;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class UserDaoImpl extends Dao<User> {

  @Autowired
  public UserDaoImpl(@Value("${user}") String userTable, RowMapper<User> userMapper,
      DataSource dataSource) {
    super(userTable, userMapper, dataSource);
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
