
import dao.TestConfig;
import com.softserve.academy.configuration.MainAppConfig;
import com.softserve.academy.dao.implementation.AbstractDao;
import javax.sql.DataSource;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


// @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainAppConfig.class})
public class TestConteiner {

  @Test
  public void getContextBeans() {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TestConfig.class);
    DataSource dataSource = (DataSource) applicationContext.getBean(DataSource.class);
    assertNotNull(dataSource);

  }
}
