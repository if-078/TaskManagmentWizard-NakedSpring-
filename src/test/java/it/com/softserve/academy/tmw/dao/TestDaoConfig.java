package it.com.softserve.academy.tmw.dao;

import it.com.softserve.academy.tmw.dao.utility.CommentPopulator;
import it.com.softserve.academy.tmw.dao.utility.PriorityPopulator;
import it.com.softserve.academy.tmw.dao.utility.RolePopulator;
import it.com.softserve.academy.tmw.dao.utility.StatusPopulator;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan(basePackages = {"com.softserve.academy.tmw.dao"})
public class TestDaoConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:create_db.sql").build();
  }

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
}
