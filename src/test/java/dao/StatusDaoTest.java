
package dao;

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

  @Test
  public void itShouldCreateFindOneUpdateGetAllDelete() {
    Status st1 = new Status();
    st1.setName("InProgrss");
    Status st2 = new Status();
    st2.setName("Waiting");
    assertThat(statusDao.create(st1)).isEqualTo(st1);
    assertThat(statusDao.create(st2)).isEqualTo(st2);
    assertThat(statusDao.findOne(st1.getId()).getId()).isEqualTo(st1.getId());
    st2.setId(2);
    st2.setName("Stopped");
    assertThat(statusDao.update(st2)).isTrue();
    assertThat(statusDao.getAll()).isNotEmpty();
    assertThat(statusDao.delete(5)).isTrue();

  }
}
