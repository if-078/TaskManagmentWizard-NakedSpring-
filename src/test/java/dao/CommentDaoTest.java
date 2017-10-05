package dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;

import com.softserve.academy.entity.Comment;
import com.softserve.academy.service.implementation.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.UserService;

@ContextConfiguration(classes = {TestConfig.class})
public class CommentDaoTest {

    public CommentServiceImpl commentService;

    @Before
    public void getObcetsFromContext() throws SQLException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);
        commentService = applicationContext.getBean(CommentServiceImpl.class);
    }

    @Test
    public void iTShoudExecuteNegativeTest() throws SQLException {
        int testId = 50;
        assertThat(commentService.getAll().isEmpty());
        assertThat(commentService.delete(testId)).isFalse();
    }

    @Test
    public void iTShouldCreateAndGetOneComment() throws Exception {
        // Given
        Comment comment = new Comment("Text comment 1", 1, 2);
        comment.setId(1);
        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 10, 10, 20, 30));
        // When
        commentService.create(comment);
        Comment commentActual = commentService.findOne(1);
        // Then
        assertEquals(comment.getId(), commentActual.getId());
        assertEquals(comment.getCommentText(), commentActual.getCommentText());
        assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
        assertEquals(comment.getTaskId(), commentActual.getTaskId());
        assertEquals(comment.getUserId(), commentActual.getUserId());
    }

    @Test
    public void iTShouldCreateAndUpdateOneComment() throws Exception {
        // Given
        Comment comment = new Comment("Text comment", 2, 1);
        comment.setId(1);
        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 20, 10, 20, 30));
        // When
        commentService.create(comment);
        comment.setCommentText("Text comment update");
        commentService.update(comment);
        Comment commentActual = commentService.findOne(1);
        // Then
        assertEquals(comment.getId(), commentActual.getId());
        assertEquals(comment.getCommentText(), commentActual.getCommentText());
        assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
        assertEquals(comment.getTaskId(), commentActual.getTaskId());
        assertEquals(comment.getUserId(), commentActual.getUserId());
    }

}
