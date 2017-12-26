package it.com.softserve.academy.tmw.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.dao.impl.TaskDao;
import com.softserve.academy.tmw.entity.Task;
import it.com.softserve.academy.tmw.configuration.TestConfig;
import it.com.softserve.academy.tmw.dao.utility.PriorityPopulator;
import it.com.softserve.academy.tmw.dao.utility.StatusPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import java.util.Date;
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
  private Task simpleTask;

  @Before
  public void setUp() {

    int hourFromMillSecs = 1000 * 60 * 60;
    statusPopulator.createDefaultEntity();
    priorityPopulator.createDefaultEntity();
    userPopulator.createDefaultEntity();
    simpleTask = new Task();
    simpleTask.setName("Default task name");
    simpleTask.setAssignTo(1);
    simpleTask.setCreatedDate(new Date(System.currentTimeMillis()));
    simpleTask.setPlanningDate(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
    simpleTask.setEndDate(new Date(
        System.currentTimeMillis() + (7 * hourFromMillSecs * 24))); //end date after one week
    simpleTask.setStartDate(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
    simpleTask.setEstimateTime(0);
    simpleTask.setSpentTime(0);
    simpleTask.setLeftTime(0);
    simpleTask.setParentId(0);
    simpleTask.setAuthorId(1);
    simpleTask.setProjectId(1);
    simpleTask.setPriorityId(1);
    simpleTask.setStatusId(1);

  }

  @Test
  public void shouldCreateFindDelete() {

    assertThat(dao.create(simpleTask)).isEqualTo(simpleTask);
    assertThat(dao.findOne(1)).isEqualTo(simpleTask);
    assertThat(dao.delete(1)).isTrue();
  }


}

