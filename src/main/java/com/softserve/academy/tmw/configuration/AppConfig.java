package com.softserve.academy.tmw.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Configuration
@PropertySource("classpath:mysql_connection.properties")
@EnableWebSecurity
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

  @Bean
  public JavaMailSender getJavaMailSender()
  {

    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    Authenticator authenticator = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("taskmanagementwizard@gmail.com", "165475TMW");
      }
    };
    Session session = Session.getDefaultInstance(props, authenticator);
    sender.setSession(session);
    return sender;
  }
}