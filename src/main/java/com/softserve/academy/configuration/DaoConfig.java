/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.configuration;

import com.softserve.academy.dao.mappers.TagMapper;
import com.softserve.academy.dao.mappers.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Oleg
 */
@Configuration
public class DaoConfig {
  @Bean
  public String userTable() {
    return "tmw.user";
  }

  @Bean
  public String tagTable() {
    return "tmw.tag";
  }

  @Bean
  public RowMapper userMapper() {
    return new UserMapper();
  }

  @Bean
  public RowMapper tagMapper() {
    return new TagMapper();
  }
}
