package dao;

import com.softserve.academy.dao.implementation.TaskDao;
import com.softserve.academy.entity.Task;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TaskDaoTest {

  @Autowired
  private TaskDao dao;
  Task task;

  @Before
  public void taskInit() {
    int hourFromMillSecs = 1000 * 60 * 60;
    task = new Task();
    task.setName("TestTask");
    task.setAssignTo(1);
    task.setCreatedDate(new Date(System.currentTimeMillis()));
    task.setEndDate(new Date(System.currentTimeMillis() + (7 * hourFromMillSecs * 24)));
    task.setStartDate(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
    task.setEstimateTime(new Time(2, 0, 0));
    task.setId(1);
    task.setParentId(0);
    task.setPriorityId(1);
    task.setStatusId(1);
  }

  @Test
  public void shouldCreateGetaAllUpdateFindOneDelete() throws SQLException {
    dao.create(task);
    List<Task> list = dao.getAll();
    assertThat(list.get(list.size() - 1).getName()).isNotNull().isEqualTo("TestTask");
    task.setId(list.get(list.size() - 1).getId());
    task.setName("NameAFterUpdate");
    assertThat(dao.update(task)).isTrue();
    assertThat(dao.findOne(task.getId()).getName()).isEqualTo("NameAFterUpdate");
    assertThat(dao.delete(task.getId())).isTrue();
    assertThat(list.size()).isNotEqualTo(dao.getAll().size());
  }

}

