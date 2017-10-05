package dao;

import javax.sql.DataSource;

import com.softserve.academy.entity.Priority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import utility.PriorityPopulator;
import utility.RolePopulator;
import utility.StatusPopulator;
import utility.UserPopulator;

@Configuration
@ComponentScan(basePackages = {"com.softserve.academy.dao", "com.softserve.academy.service" })
public class TestConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:create_db.sql").build();
  }

  @Bean
  public UserPopulator getUserPop(){
    return new UserPopulator();
  }

  @Bean
  public PriorityPopulator getPriorityPopulator(){
    return new PriorityPopulator();
  }

  @Bean
  public StatusPopulator getStatusPopulator(){
    return new StatusPopulator();
  }

  @Bean
  public RolePopulator getRolePop(){
    return new RolePopulator();
  }
}