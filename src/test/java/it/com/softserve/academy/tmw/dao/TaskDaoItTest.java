package it.com.softserve.academy.tmw.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.dao.impl.TaskDao;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Task;
import it.com.softserve.academy.tmw.dao.utility.PriorityPopulator;
import it.com.softserve.academy.tmw.dao.utility.StatusPopulator;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
public class TaskDaoItTest {

  @Autowired
  private TaskDao dao;
  @Autowired
  private CommentDao commentDao;
  @Autowired
  public UserPopulator userPopulator;
  @Autowired
  private StatusPopulator statusPopulator;
  @Autowired
  private PriorityPopulator priorityPopulator;
  @Autowired
  private TaskPopulator taskPopulator;

  @Before
  public void setUp() {
    statusPopulator.createDefaultEntity();
    priorityPopulator.createDefaultEntity();
    userPopulator.createDefaultEntity();

  }

  /*
  @Test(expected = EmptyResultDataAccessException.class)
  public void shouldCreateFindUpdateDeleteAndGetAllAndThrowExeption() throws SQLException {
    String taskName = "TestTask";
    Date date = new Date();
    Timestamp sqlDate = new Timestamp(date.getTime());
    int estTime = 0;
    Task task1 = new Task(taskName + 1, sqlDate, sqlDate, sqlDate, sqlDate, estTime, 1, 1, 1, 0);
    task1.setId(1);
    Task task2 = new Task(taskName + 2, sqlDate, sqlDate, sqlDate, sqlDate, estTime, 1, 1, 1, 0);
    task2.setId(2);
    Task task3 = new Task(taskName + 3, sqlDate, sqlDate, sqlDate, sqlDate, estTime, 1, 1, 1, 0);
    task3.setId(43);

    dao.create(task1);
    dao.create(task2);
    dao.create(task3);
    System.out.println(dao.findOne(1));
    assertThat(dao.findOne(4)).isEqualTo(task1);
    assertThat(dao.findOne(5)).isEqualTo(task2);
    assertThat(dao.findOne(6)).isEqualTo(task3);
    task3.setName(taskName + " update");
    assertThat(dao.update(task3)).isTrue();
    assertThat(dao.getAll()).isNotEmpty();
    assertThat(dao.delete(task3.getId())).isTrue();
    assertThat(dao.getAll().size()).isEqualTo(5);

    assertThat(dao.delete(task1.getId())).isTrue();
    assertThat(dao.delete(task2.getId())).isTrue();
    assertThat(dao.delete(task3.getId())).isFalse();
    assertThat(dao.update(task3)).isFalse();
    dao.findOne(777);
  }
*/

  @Test
  public void shouldAddCommentAndGetComment() {

    taskPopulator.createDefaultEntity();
    System.out.println(dao.findOne(1));
    Comment comment1 = new Comment("Comment1", 1, 1);
    Comment comment2 = new Comment("Comment2", 1, 1);
    Comment comment3 = new Comment("Comment3", 1, 1);
    LocalDateTime createdDate = LocalDateTime.now();
    comment1.setCreatedDate(createdDate);
    comment2.setCreatedDate(createdDate);
    comment3.setCreatedDate(createdDate);
    List<Comment> commentsList = new ArrayList<Comment>();
    commentsList.add(comment1);
    commentsList.add(comment2);
    commentsList.add(comment3);
    commentDao.create(comment1);
    commentDao.create(comment2);
    assertThat(commentDao.create(comment3)).isEqualTo(comment3);
    for (int i = 0; i < commentsList.size(); i++) {
      assertThat(dao.getCommentsOfTask(1).get(i).getCommentText())
          .isEqualTo(commentsList.get(i).getCommentText());
    }

  }

}

