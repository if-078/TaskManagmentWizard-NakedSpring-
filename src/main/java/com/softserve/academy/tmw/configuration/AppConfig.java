package com.softserve.academy.tmw.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

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
    PoolConfiguration configuration= new PoolProperties();
    configuration.setMaxActive(1000);
    configuration.setMaxIdle(900);
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

  @Bean
  public JavaMailSender getJavaMailSender()
  {

    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.user", "taskmanagementwizard@gmail.com");
    properties.setProperty("mail.smtp.password", "165475TMW");
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtps.ssl.enable", "true");
    properties.setProperty("mail.smtp.port", "587");
    properties.setProperty("mail.smtp.host", "smtp.gmail.com");

    sender.setJavaMailProperties(properties);

    return sender;
  }

}
