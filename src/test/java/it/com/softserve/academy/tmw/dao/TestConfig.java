package it.com.softserve.academy.tmw.dao;

import com.softserve.academy.tmw.controller.TagController;
import it.com.softserve.academy.tmw.dao.utility.CommentPopulator;
import it.com.softserve.academy.tmw.dao.utility.PriorityPopulator;
import it.com.softserve.academy.tmw.dao.utility.RolePopulator;
import it.com.softserve.academy.tmw.dao.utility.StatusPopulator;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.softserve.academy.tmw.dao","com.softserve.academy.tmw.service"})
public class TestConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:create_db.sql").build();
  }

  @Bean
  public TagController getTagController() {return new TagController();}

  @Bean
  public UserPopulator getUserPop() {
    return new UserPopulator();
  }

  @Bean
  public PriorityPopulator getPriorityPopulator() {
    return new PriorityPopulator();
  }

  @Bean
  public StatusPopulator getStatusPopulator() {
    return new StatusPopulator();
  }

  @Bean
  public RolePopulator getRolePop() {
    return new RolePopulator();
  }

  @Bean
  public TaskPopulator getTaskPopulator() {
    return new TaskPopulator();
  }

  @Bean
  public CommentPopulator getCommentPopulator() {
    return new CommentPopulator();
  }

  @Bean
  public PasswordEncoder getEncoder() {
    return new BCryptPasswordEncoder();
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
