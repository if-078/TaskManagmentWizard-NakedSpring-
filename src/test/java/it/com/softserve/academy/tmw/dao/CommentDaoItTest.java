package it.com.softserve.academy.tmw.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.entity.Comment;
import it.com.softserve.academy.tmw.configuration.TestConfig;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CommentDaoItTest {

  @Autowired
  private CommentDao dao;
  @Autowired
  private TaskPopulator taskPopulator;
  @Autowired
  private UserPopulator userPopulator;
  private List<Comment> comments;
  private int testId;

  @Before
  public void createUserAndTask() {
    userPopulator.createDefaultEntity();
    taskPopulator.createDefaultEntity();
    testId = 1;
    comments = new ArrayList();
    comments.add(new Comment("Comment1", testId, testId));
    comments.add(new Comment("Comment2", testId, testId));
    comments.add(new Comment("Comment3", testId, testId));
    comments.add(new Comment("Comment4", testId, testId));
  }

  @Test
  public void shouldCreateFindAndDelete() {

    comments.forEach(item -> assertThat(dao.create(item)).isEqualTo(item));

    assertThat(dao.getCommentsByTaskId(testId)).isEqualTo(comments);

    assertThat(dao.delete(1)).isTrue();
    assertThat(dao.delete(2)).isTrue();

    assertThat(dao.getCommentsByTaskId(testId).get(0)).isEqualTo(comments.get(2));
  }


}
