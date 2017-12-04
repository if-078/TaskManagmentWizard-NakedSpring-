package it.com.softserve.academy.tmw.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.softserve.academy.tmw.dao.impl.TagDao;
import com.softserve.academy.tmw.dao.impl.TaskDao;
import com.softserve.academy.tmw.entity.Tag;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TagDaoItTest {

  @Autowired
  public TagDao tagDao;
  @Autowired
  public TaskDao taskDao;
  @Autowired
  public UserPopulator userPopulator;
  @Autowired
  private TaskPopulator taskPopulator;


  @Before
  public void createFewUsers() {
    userPopulator.createDefaultEntity();
    userPopulator.createDefaultEntity();
    userPopulator.createDefaultEntity();
    taskPopulator.createDefaultEntity();

  }

  @Test
  public void shouldCreateFindOneAndAllUpdateAndDelete() {
    List<Tag> tagsForTest = new ArrayList<>();
    tagsForTest.add(new Tag(1, "#Cat", 1, 1));
    tagsForTest.add(new Tag(2, "#bicycle", 1, 1));
    tagsForTest.add(new Tag(3, "#Books", 1, 1));
    tagsForTest.add(new Tag(4, "#Sword", 2, 1));
    tagsForTest.add(new Tag(5, "#Bow", 2, 1));
    tagsForTest.add(new Tag(6, "#Axe", 3, 1));
    tagsForTest.add(new Tag(7, "#Knife", 3, 1));
    tagsForTest.add(new Tag(8, "#Searching", 3, 1));
    tagsForTest
        .forEach(item -> assertThat(tagDao.create(item).getName()).isEqualTo(item.getName()));
    tagsForTest.forEach(item -> assertThat(tagDao.findOne(item.getId())).isEqualTo(item));

    assertThat(tagDao.findOne(tagsForTest.get(7).getId()).getName()).isEqualTo("#Searching");
    assertThat(tagDao.delete(4)).isTrue();
    assertThat(tagDao.getAllByUserId(3)).hasSize(3);
    assertThat(tagDao.update(new Tag(1, "#Cat2", 2, 1))).isTrue();
    assertThat(tagDao.deleteAllByUserId(1)).isTrue();
  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void shouldNOTCreateFindOneAndAllUpdateAndDelete() {
    assertThat(tagDao.getAllByUserId(9999)).isEmpty();
    assertThat(tagDao.deleteAllByUserId(9999)).isFalse();
    assertThat(tagDao.update(new Tag(2, "atata", 9999, 1))).isFalse();
    assertThat(tagDao.delete(100)).isFalse();
    assertThat(tagDao.findOne(1000).getId()).isIn(0);
  }

  @Test
  public void shouldAddTagsToTaskAndGetTags() {
    userPopulator.createDefaultEntity();
    List<Tag> tags = new ArrayList<>();
    tags.add(new Tag(1, "#TestTag1", 1, 1));
    tags.add(new Tag(2, "#TestTag2", 1, 1));
    tagDao.create(tags.get(0));
    tagDao.create(tags.get(1));
    assertThat(tagDao.setTagsToTask(tags, 1)).isTrue();
    assertThat(taskDao.getTagsOfTask(1)).isEqualTo(tags);

  }

}