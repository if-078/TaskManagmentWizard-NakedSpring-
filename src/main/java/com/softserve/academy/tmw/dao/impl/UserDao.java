package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.UserDaoInterface;
import com.softserve.academy.tmw.dao.mapper.UserMapper;
import com.softserve.academy.tmw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Repository
@PropertySource("classpath:tables.properties")
public class UserDao extends EntityDao<User> implements UserDaoInterface {

  public UserDao(@Value("${uuser}") String table) {
    super(table, new UserMapper());
  }

  @Autowired
  private JavaMailSender mailSender;


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

  public boolean verifyUser (int id, long key){
    String sql = "update " + table + "set active = 1 where id = (select user_id from user_activation where user_key = :key)and user_id = :user_id";
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();
    parameterSource.addValue("key", key);
    parameterSource.addValue("user_id", id);
    return jdbcTemplate.update(sql, parameterSource) >0;
  }

}