package dao;


import com.softserve.academy.dao.implementation.*;
import com.softserve.academy.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


// @RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {TestConfig.class})

public class TagDaoTest {

  @Autowired
  public TagDaoimpl tagDao;
  @Autowired
  public UserDaoImpl userDao;

  @Test
  public void shouldCreate4TagsGetAllDeleteUpdateAndGet() {
    userDao.create(new User("Oleg", "qazsxw", "someMail"));
  }
}
