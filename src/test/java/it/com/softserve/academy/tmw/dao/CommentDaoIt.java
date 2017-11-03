package it.com.softserve.academy.tmw.dao;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.entity.Comment;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
public class CommentDaoIt {

    @Autowired
    public CommentDao commentDao;
    @Autowired
    public UserPopulator populatorUser;
    @Autowired
    public TaskPopulator populatorTask;

    @Before
    public void createFewUsers() {
        populatorUser.createDefaultEntity();
        populatorTask.createDefaultEntity();

    }


    public void iTShoudExecuteNegativeTest() throws SQLException {
        int testId = 50;
        assertThat(commentDao.getAll().isEmpty());
        assertThat(commentDao.delete(testId)).isFalse();
    }


    public void iTShouldCreateAndGetOneComment() throws Exception {
        // Given
        Comment comment = new Comment("Text comment 1", 1, 1);

        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 10, 10, 20, 30));
        // When
        int id = commentDao.create(comment).getId();
        comment.setId(id);

        Comment commentActual = commentDao.findOne(id);
        // Then
        assertEquals(comment.getId(), commentActual.getId());
        assertEquals(comment.getCommentText(), commentActual.getCommentText());
        assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
        assertEquals(comment.getTaskId(), commentActual.getTaskId());
        assertEquals(comment.getUserId(), commentActual.getUserId());
        commentDao.delete(id);
    }


    public void iTShouldCreateAndUpdateOneComment() throws Exception {
        // Given
        Comment comment = new Comment("Text comment", 1, 1);
        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 20, 10, 20, 30));
        // When
        int id = commentDao.create(comment).getId();
        comment.setId(id);

        comment.setCommentText("Text comment update");
        commentDao.update(comment);
        Comment commentActual = commentDao.findOne(id);
        // Then
        assertEquals(comment.getId(), commentActual.getId());
        assertEquals(comment.getCommentText(), commentActual.getCommentText());
        assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
        assertEquals(comment.getTaskId(), commentActual.getTaskId());
        assertEquals(comment.getUserId(), commentActual.getUserId());
        commentDao.delete(id);
    }



    public void iTShouldCreateAndReadAllAndDeleteComment() throws Exception {
        // Given
        Comment comment;
        List<Comment> comments = new ArrayList<>();

        comment = new Comment("Comment text 1", 1, 1);
        comment.setId(3);
        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 10, 10, 20, 30));
        comments.add(comment);

        comment = new Comment("Comment text 2", 1, 1);
        comment.setId(4);
        comment.setCreatedDate(LocalDateTime.of(2016, Month.SEPTEMBER, 15, 15, 30, 40));
        comments.add(comment);

        comment = new Comment("Comment text 3", 1, 1);
        comment.setId(5);
        comment.setCreatedDate(LocalDateTime.of(2017, Month.SEPTEMBER, 20, 20, 40, 50));
        comments.add(comment);

        // When
        for (int i = 0; i < comments.size(); i++) {
            commentDao.create(comments.get(i));
        }
        List<Comment> commentsActual = commentDao.getAll();

        // Then
        assertEquals(comments.size(), commentsActual.size());
        for (int i = 0; i < commentsActual.size(); i++) {
            assertEquals(comments.get(i).getId(), commentsActual.get(i).getId());
            assertEquals(comments.get(i).getCommentText(), commentsActual.get(i).getCommentText());
            assertEquals(comments.get(i).getCreatedDate(), commentsActual.get(i).getCreatedDate());
            assertEquals(comments.get(i).getTaskId(), commentsActual.get(i).getTaskId());
            assertEquals(comments.get(i).getUserId(), commentsActual.get(i).getUserId());
            commentDao.delete(commentsActual.get(i).getId());
        }
        commentsActual = commentDao.getAll();
        assertEquals(0, commentsActual.size());

    }


}