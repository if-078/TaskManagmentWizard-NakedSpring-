
package dao;

import com.softserve.academy.dao.implementation.PriorityDao;
import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.entity.Status;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class StatusDaoTest {

  @Autowired
  public StatusDao statusDao;
  @Autowired
  public PriorityDao priorityDao;



  // @Test
  // public void simpleTest(){
  // Priority p1 = new Priority(1, "Low");
  // Priority p2 = new Priority(2, "High");
  // priorityDao.create(p1);
  // priorityDao.create(p2);
  // assertThat(priorityDao.findOne(1).getId()).isEqualTo(1);
  // }


  @Test
  public void simpleTest2() {
    Status st1 = new Status();
    st1.setId(1);
    st1.setName("atata");
    assertThat(statusDao.create(st1)).isEqualTo(st1);
  }
}
