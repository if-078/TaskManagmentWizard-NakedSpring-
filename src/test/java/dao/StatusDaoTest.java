
package dao;

import com.softserve.academy.dao.implementation.RoleDaoImpl;
import com.softserve.academy.dao.implementation.StatusDao;
import java.sql.SQLException;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TestConfig.class})
public class StatusDaoTest {
    
      public StatusDao statusDao;
      public RoleDaoImpl roleDao;

  @Before
  public void getObcetsFromContext() throws SQLException {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TestConfig.class);
    statusDao = applicationContext.getBean(StatusDao.class);
     roleDao = applicationContext.getBean(RoleDaoImpl.class);
 
  }
}
