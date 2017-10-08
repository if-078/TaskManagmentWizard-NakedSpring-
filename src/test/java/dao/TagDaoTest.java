package dao;

import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.User;
import com.softserve.academy.service.implementation.TagService;
import java.sql.SQLException;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utility.Populator;
import utility.UserPopulator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TagDaoTest {

  @Autowired
  public TagService tagService;
  @Autowired
  public Populator<User> populator;

  @Before
  public void createFewUsers() {
    populator.createDefaultEntity();
    populator.createDefaultEntity();
    populator.createDefaultEntity();

  }

  @Test
  public void lightNegativeTesting() {
    assertThat(tagService.getAllByUserId(100)).isEmpty();
    assertThat(tagService.deleleAllByUserId(100)).isFalse();
    assertThat(tagService.update(new Tag(2, "atata", 1))).isFalse();
    assertThat(tagService.delete(100)).isFalse();
    assertThat(tagService.findOne(1000)).isNull();
  }

  @Test
  public void loadDAOAndUSerFromContextAndGetSetItToH2DB() {
    tagService.create(new Tag(1, "#Cat", 1));
    tagService.create(new Tag(2, "#bicycle", 1));
    tagService.create(new Tag(3, "#Books", 1));
    tagService.create(new Tag(4, "#Sword", 2));
    tagService.create(new Tag(5, "#Bow", 2));
    tagService.create(new Tag(6, "#Axe", 2));
    tagService.create(new Tag(7, "#Knife", 3));
    tagService.create(new Tag(8, "#Searching", 3));
    assertThat(tagService.findOne(8).getName()).isEqualTo("#Searching");
    assertThat(tagService.create(new Tag(9, "#Books", 3)).getId()).isEqualTo(9);
    assertThat(tagService.delete(4)).isTrue();
    assertThat(tagService.getAllByUserId(2)).hasSize(2);
    assertThat(tagService.update(new Tag(1, "#Cat2", 2))).isTrue();
    assertThat(tagService.deleleAllByUserId(1)).isTrue();

  }

  @Test
  public void hardNegativeTesting() {
    // tagService.create(new Tag(7, "#Knife", 99));
    // tagService.update(new Tag(7, "#Knife", 100));
  }
}

