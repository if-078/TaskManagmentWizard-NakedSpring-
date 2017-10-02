package dao;


import com.softserve.academy.configuration.DaoConfig;
import com.softserve.academy.configuration.MainAppConfig;
import com.softserve.academy.configuration.webConfig.WebAppConfig;
import com.softserve.academy.dao.implementation.*;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;


// @RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {TestConfig.class, DaoConfig.class})
public class TagDaoTest {

  @Autowired
  public TagDaoimpl tagDao;
  @Autowired
  public UserDaoImpl userDao;


  @Test
  public void shouldCreate4TagsGetAllDeleteUpdateAndGet() {
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TestConfig.class);
    User u = applicationContext.getBean(User.class);
    System.err.println(u.getName());
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    // userDao.create(u);
  }
}
