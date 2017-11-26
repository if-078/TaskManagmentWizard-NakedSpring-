package it.com.softserve.academy.tmw.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.softserve.academy.tmw.dao.impl.StatusDao;
import com.softserve.academy.tmw.entity.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class StatusDaoItTest {

  @Autowired
  public StatusDao statusDao;

  @Test
  public void itShouldCreateFindOneUpdateGetAllandDelete() {
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
    assertThat(statusDao.delete(2)).isTrue();

  }
}
