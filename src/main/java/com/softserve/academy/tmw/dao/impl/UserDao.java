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

    System.out.println(entity.getId());
    long key =java.util.UUID.randomUUID().hashCode();
    String notVerifaed = "insert into tmw.user_activation (user_id, user_key) values (:user_id, :user_key)";
    MapSqlParameterSource source = new MapSqlParameterSource();
    source.addValue("user_key", key);
    source.addValue("user_id", entity.getId());

    MimeMessage message = mailSender.createMimeMessage();
    String messageLink = "http:localhost:8585/api/users/verify/" + entity.getId()+ "?key=";

    try {
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(entity.getEmail()));
      message.setSubject("email verification");

      messageLink = messageLink+ key;
      message.setContent("Please click link below to confirm your email verification + \n " +
             "<a href="+ messageLink + ">Your link for verify</a>",  "text/html");
      mailSender.send(message);
      jdbcTemplate.update(notVerifaed, source);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
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