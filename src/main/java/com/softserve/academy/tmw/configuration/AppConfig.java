package com.softserve.academy.tmw.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@PropertySource("classpath:mysql_connection.properties")
public class AppConfig {

  @Value("${jdbc.schema}")
  private String schema;
  @Value("${jdbc.driver}")
  private String driver;
  @Value("${jdbc.url}")
  private String url;
  @Value("${jdbc.username}")
  private String username;
  @Value("${jdbc.password}")
  private String password;
  

  @Bean
  public DataSource getDataSource() {
    DataSource dataSource = new DataSource();
    dataSource.setDriverClassName(driver);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    dataSource.setRemoveAbandoned(true);
    dataSource.setInitialSize(10);
    dataSource.setMaxActive(20);
    return dataSource;
  }

  @Bean
  public DataSourceTransactionManager getDataSourceTransactionManager() {
    return new DataSourceTransactionManager(getDataSource());
  }

  @Bean
  public ObjectMapper getJsonMapper() {
    return new ObjectMapper();
  }
}
