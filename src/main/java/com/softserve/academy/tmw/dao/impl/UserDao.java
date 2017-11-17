package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.UserDaoInterface;
import com.softserve.academy.tmw.dao.mapper.UserMapper;
import com.softserve.academy.tmw.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class UserDao extends EntityDao<User> implements UserDaoInterface {

  public UserDao(@Value("${uuser}") String table) {
    super(table, new UserMapper());
  }

  @Override
  public User create(User entity) {
    String sql = "INSERT INTO " + table + " (name, pass, email) VALUES (:name, :pass, :email)";
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    param.addValue("name", entity.getName());
    param.addValue("pass", entity.getPass());
    param.addValue("email", entity.getEmail());
    jdbcTemplate.update(sql, param, keyHolder);
    entity.setId(keyHolder.getKey().intValue());

    return entity;

  }

  @Override
  public boolean update(User entity) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    String sql =
        "UPDATE " + table + " SET name = :name, pass = :pass, email = :email WHERE id = :id";
    param.addValue("name", entity.getName());
    param.addValue("pass", entity.getPass());
    param.addValue("email", entity.getEmail());
    param.addValue("id", entity.getId());

    return jdbcTemplate.update(sql, param) == 1;

  }

  @Override
  public User findByEmail(String email) {
    String sql = "SELECT * FROM " + table + " WHERE email = :email";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("email", email);

    return jdbcTemplate.queryForObject(sql, param, super.mapper);
  }

}