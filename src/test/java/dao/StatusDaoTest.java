
package dao;

import com.softserve.academy.dao.implementation.PriorityDao;
import com.softserve.academy.dao.implementation.RoleDaoImpl;
import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.entity.Priority;
import com.softserve.academy.entity.Status;
import java.math.BigDecimal;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.*;
@ContextConfiguration(classes = {TestConfig.class})
public class StatusDaoTest {
    
      public StatusDao statusDao;
      public PriorityDao priorityDao;

  @Before
  public void getObcetsFromContext() throws SQLException {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TestConfig.class);
    statusDao = applicationContext.getBean(StatusDao.class);
     priorityDao = applicationContext.getBean(PriorityDao.class);
 
  }
  
  @Test
  public void simpleTest(){
      Priority p1 = new Priority(1, "Low");
      Priority p2 = new Priority(2, "High");
      priorityDao.create(p1);
      priorityDao.create(p2);
          assertThat(priorityDao.findOne(1).getId()).isEqualTo(1);
  }
  
    
  @Test
  public void simpleTest2(){
      Status st1 = new Status();
      st1.setId(1);
      st1.setName("atata");
        assertThat(statusDao.create(st1)).isEqualTo(st1);
  }
}
