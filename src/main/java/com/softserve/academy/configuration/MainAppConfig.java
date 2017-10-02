package com.softserve.academy.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:mysql_connection.properties")
public class MainAppConfig {
  @Autowired
  private Environment env;

  @Bean
  public DataSource getDataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setSchema(env.getProperty("jdbc.schema"));
    driverManagerDataSource.setDriverClassName(env.getProperty("jdbc.driver"));
    driverManagerDataSource.setUrl(env.getProperty("jdbc.url"));
    driverManagerDataSource.setUsername(env.getProperty("jdbc.username"));
    driverManagerDataSource.setPassword(env.getProperty("jdbc.password"));
    return driverManagerDataSource;
  }

  @Bean
  public ObjectMapper getJsonMapper() {
    return new ObjectMapper();
  }
}
