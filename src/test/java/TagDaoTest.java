
import com.softserve.academy.configuration.DaoConfiguration;
import com.softserve.academy.configuration.MainAppConfig;
import com.softserve.academy.dao.implementation.TagDaoimpl;
import com.softserve.academy.dao.implementation.UserDaoImpl;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.User;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainAppConfig.class, DaoConfiguration.class})
public class TagDaoTest {

  @Autowired
  TagDaoimpl daoimpl;
  @Autowired
  UserDaoImpl daoimpl2;

  @Test
  public void shouldCreate4TagsGetAllDeleteUpdateAndGet() throws SQLException {
    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    System.err.println(daoimpl2.create(new User("Oleg", "123", "nana@mail")));
    System.err.println(daoimpl2.create(new User("Ingret", "123", "nana@mail")));
    Tag t1 = new Tag("#Java11", 1);
    Tag t2 = new Tag("#Java12", 1);
    Tag t3 = new Tag("#Java21", 2);
    Tag t4 = new Tag("#Java22", 2);
    Tag updateTAg = new Tag("#JavaU", 2);
    updateTAg.setId(2);
    System.err.println(daoimpl.create(t1));
    System.err.println(daoimpl.create(t2));
    System.err.println(daoimpl.create(t3));
    System.err.println(daoimpl.create(t4));
    List<Tag> tagList = daoimpl.getAllByUserId(2);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    for (Tag tag : tagList) {
      System.out.println(tag);
    }
    // Assert.assertEquals(t3.name, tagList.get(0).name);
    // Assert.assertEquals(t4.name, tagList.get(1).name);
    tagList = daoimpl.findAll();
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    for (Tag tag : tagList) {
      System.out.println(tag);
    }
    Assert.assertEquals(t1.name, tagList.get(0).name);
    Assert.assertEquals(t2.name, tagList.get(1).name);
    daoimpl.delete(4);
    daoimpl.delete(3);
    daoimpl.update(updateTAg);
    Assert.assertEquals(updateTAg.name, daoimpl.findOne(2).name);
    daoimpl.create(new Tag("template", 2));
    tagList = daoimpl.getAllByUserId(2);
    for (Tag tag : tagList) {
      System.out.println(tag.name);
    }

  }

  @Test
  public void getGetAllNEgativeTest() throws SQLException {

    // Assert.assertEquals(null, dao.find(22));
    // Assert.assertEquals(new ArrayList<>().isEmpty(), dao.getAllByUserId(5).isEmpty());
    // dao.create(new Tag("sasas", 110));



  }
}
