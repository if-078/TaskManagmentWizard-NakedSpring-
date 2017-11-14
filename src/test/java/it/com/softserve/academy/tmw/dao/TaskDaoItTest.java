package it.com.softserve.academy.tmw.dao;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.dao.impl.TaskDao;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Task;
import it.com.softserve.academy.tmw.dao.utility.PriorityPopulator;
import it.com.softserve.academy.tmw.dao.utility.StatusPopulator;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
public class TaskDaoItTest {

    @Autowired
    private TaskDao dao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private TaskPopulator taskPopulator;
    @Autowired
    public UserPopulator userPopulator;
    @Autowired
    private StatusPopulator statusPopulator;
    @Autowired
    private PriorityPopulator priorityPopulator;

    @Before
    public void setUp(){
        statusPopulator.createDefaultEntity();
        priorityPopulator.createDefaultEntity();
        //taskPopulator.createDefaultEntity();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldCreateFindUpdateDeleteAndGetAllAndThrowExeption() throws SQLException {
        String taskName = "TestTask";
        Date date = new Date();
        Timestamp sqlDate = new Timestamp(date.getTime());
        Time estTime = new Time(date.getTime());
        Task task1 = new Task(taskName+1, sqlDate, sqlDate, sqlDate, sqlDate, estTime, 1,1,1,0);
        task1.setId(1);
        Task task2 = new Task(taskName+2, sqlDate, sqlDate, sqlDate, sqlDate, estTime, 1,1,1,0);
        task2.setId(2);
        Task task3 = new Task(taskName+3, sqlDate, sqlDate, sqlDate, sqlDate, estTime, 1,1,1,0);
        task3.setId(43);

        dao.create(task1);
        dao.create(task2);
        dao.create(task3);
        assertThat(dao.findOne(1)).isEqualTo(task1);
        assertThat(dao.findOne(2)).isEqualTo(task2);
        assertThat(dao.findOne(3)).isEqualTo(task3);
        task3.setName(taskName+" update");
        assertThat(dao.update(task3)).isTrue();
        assertThat(dao.getAll()).isNotEmpty();
        assertThat(dao.delete(task3.getId())).isTrue();
        assertThat(dao.getAll().size()).isEqualTo(2);

        assertThat(dao.delete(task1.getId())).isTrue();
        assertThat(dao.delete(task2.getId())).isTrue();
        assertThat(dao.delete(task3.getId())).isFalse();
        assertThat(dao.update(task3)).isFalse();
        assertThat(dao.getAll()).isEmpty();
        dao.findOne(777);
    }

    @Test
    public void shouldAddCommentAndGetComment() {
        taskPopulator.createDefaultEntity();
        Comment comment1 = new Comment("Comment1", 4, 1);
        Comment comment2 = new Comment("Comment2", 4, 1);
        Comment comment3 = new Comment("Comment3", 4, 1);
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
            assertThat(dao.getCommentsOfTask(4).get(i).getCommentText()).isEqualTo(commentsList.get(i).getCommentText());
        }


    }


}

