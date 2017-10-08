package dao;

import com.softserve.academy.dao.implementation.TaskDao;
import com.softserve.academy.entity.Task;
import com.softserve.academy.service.implementation.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import java.sql.SQLException;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TaskDaoTest {

  @Autowired
  private TaskDao dao;
  private TaskService serv;


  @Test
  public void getObcetsFromContext() throws SQLException {
    Date date = new Date();
    java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
    java.sql.Time estTime = new java.sql.Time(date.getTime());
  }
  // dao.create(new Task("add smth6", sqlDate, sqlDate, sqlDate, estTime, 1, 1, 1, 0));
  // dao.create(new Task("add smth7", sqlDate, sqlDate, sqlDate, estTime, 1, 1, 1, 0));
  // dao.create(new Task("add smth8", sqlDate, sqlDate, sqlDate, estTime, 1, 1, 1, 0));
  // }
  //
  //
  // @Test
  // public void lightNegativeTesting() {
  // assertThat(dao.findOne(2)).isNotNull();
  // assertThat(dao.delete(100)).isFalse();
  // // assertThat(dao.update(new Tag(2, "atata", 1))).isFalse();
  // assertThat(dao.delete(2)).isTrue();
  // }
  //
  //
  // @Test
  // public void aaddTask() throws SQLException {
  // Date date = new Date();
  // java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
  // java.sql.Time estTime = new java.sql.Time(date.getTime());
  // Task task = new Task("add smth6", sqlDate, sqlDate, sqlDate, estTime, 1, 1, 1, 0);
  // String result = dao.create(task).getName();
  // assertEquals("add smth6", result);
  // }
  //
  // // @Test
  // // public void findTaskById() {
  // // Task task = new Task();
  // // task.setId(1);
  // // String result = dao.findOne(1).getName();
  // // assertEquals("add smth6", result);
  // // }
  //
  // @Test
  // public void deleteTask() {
  // Task task = new Task();
  // task.setId(5);
  // boolean result = dao.delete(3);
  // assertEquals(true, result);
  // }


}

/*
 * CONSTRAINT `fk_Tasks_Status1` FOREIGN KEY (`status_id`) REFERENCES tmw.status(`id`) ON DELETE
 * CASCADE ON UPDATE CASCADE, CONSTRAINT `fk_Tasks_Priorities1` FOREIGN KEY (`priority_id`)
 * REFERENCES tmw.priority (`id`) ON DELETE RESTRICT ON UPDATE CASCADE)
 */
