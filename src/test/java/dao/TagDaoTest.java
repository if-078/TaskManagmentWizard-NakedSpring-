package dao;

import com.softserve.academy.dao.implementation.*;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.User;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.*;

@ContextConfiguration(classes = {TestConfig.class})
public class TagDaoTest {

  public TagDaoimpl tagDao;
  public UserDaoImpl userDao;

  @Test
  public void loadDAOAndUSerFromContextAndGetSetItToH2DB() {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(TestConfig.class);
    userDao = applicationContext.getBean(UserDaoImpl.class);
    tagDao = applicationContext.getBean(TagDaoimpl.class);

    userDao.create(new User("Ingret", "12", "email@lala.du"));
    userDao.create(new User("Ragnar", "92", "email@lala.eu"));
    userDao.create(new User("Garret", "35", "email@gmail.uu"));
    tagDao.create(new Tag(1, "#Cat", 1));
    tagDao.create(new Tag(2, "#bicycle", 1));
    tagDao.create(new Tag(3, "#Books", 1));
    tagDao.create(new Tag(4, "#Sword", 2));
    tagDao.create(new Tag(5, "#Bow", 2));
    tagDao.create(new Tag(6, "#Axe", 2));
    tagDao.create(new Tag(7, "#Knife", 3));
    tagDao.create(new Tag(8, "#Searching", 3));
    assertThat(tagDao.create(new Tag(9, "#Books", 3)).getId()).isEqualTo(9);
    List<Tag> list = tagDao.getAll();
    list.stream().forEach(System.out::println);
    assertThat(list).hasSize(9);
    tagDao.delete(4);
    assertThat(tagDao.getAllByUserId(2)).hasSize(2);
    assertThat(tagDao.update(new Tag(1, "#Cat2", 2))).isTrue();


  }
}
