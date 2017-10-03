package dao;


import com.softserve.academy.dao.config.DaoConfig;
import com.softserve.academy.dao.implementation.*;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;


// @RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {TestConfig.class})
public class TagDaoTest {

  public TagDaoimpl tagDao;
  public UserDaoImpl userDao;

  @Test
  public void loadDAOAndUSerFromContextAndGetSetItToH2DB() {
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TestConfig.class);
    User u = (User) applicationContext.getBean("iwan");
    userDao = applicationContext.getBean(UserDaoImpl.class);
    tagDao = applicationContext.getBean(TagDaoimpl.class);
    System.err.println(u);
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    userDao.create(u);
    userDao.create(new User("Namw", "Pass", "mail"));
    tagDao.create(new Tag("#Kurva", 1));
    userDao.getAll().stream().forEach((u1) -> {
      System.out.println(u1);
    });
    System.out.println(tagDao.findOne(1));
  }
}
