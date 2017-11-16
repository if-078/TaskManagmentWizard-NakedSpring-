package com.softserve.academy.tmw.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setSchema(schema);
    driverManagerDataSource.setDriverClassName(driver);
    driverManagerDataSource.setUrl(url);
    driverManagerDataSource.setUsername(username);
    driverManagerDataSource.setPassword(password);
    return driverManagerDataSource;
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
