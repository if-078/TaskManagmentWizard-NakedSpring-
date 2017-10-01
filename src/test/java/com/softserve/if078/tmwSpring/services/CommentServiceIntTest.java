package com.softserve.if078.tmwSpring.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.softserve.if078.tmwSpring.configurations.H2DbConfig;
import com.softserve.if078.tmwSpring.entities.Comment;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.softserve.if078.tmwSpring")
@SpringBootTest(classes = {H2DbConfig.class })
@EnableConfigurationProperties
public class CommentServiceIntTest {

    @Autowired
    CommentService commentService;

    @Test
    public void iTShouldCreateAndGetOneComment() throws Exception {
        // Given
        Comment comment = new Comment("Text comment 1", 1, 2);
        comment.setCommentId(1);
        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 10, 10, 20, 30));
        // When
        commentService.create(comment);
        Comment commentActual = commentService.get(1);
        // Then
        assertEquals(comment.getCommentId(), commentActual.getCommentId());
        assertEquals(comment.getCommentText(), commentActual.getCommentText());
        assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
        assertEquals(comment.getTaskId(), commentActual.getTaskId());
        assertEquals(comment.getUserId(), commentActual.getUserId());
    }

    @Test
    public void iTShouldCreateAndUpdateOneComment() throws Exception {
        // Given
        Comment comment = new Comment("Text comment", 2, 1);
        comment.setCommentId(1);
        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 20, 10, 20, 30));
        // When
        commentService.create(comment);
        comment.setCommentText("Text comment update");
        commentService.update(comment);
        Comment commentActual = commentService.get(1);
        // Then
        assertEquals(comment.getCommentId(), commentActual.getCommentId());
        assertEquals(comment.getCommentText(), commentActual.getCommentText());
        assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
        assertEquals(comment.getTaskId(), commentActual.getTaskId());
        assertEquals(comment.getUserId(), commentActual.getUserId());
    }

    @Test

    public void iTShouldCreateAndReadAllAndDeleteComment() throws Exception {
        // Given
        Comment comment;
        List<Comment> comments = new ArrayList<>();

        comment = new Comment("Comment text 1", 1, 2);
        comment.setCommentId(1);
        comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 10, 10, 20, 30));
        comments.add(comment);

        comment = new Comment("Comment text 2", 2, 3);
        comment.setCommentId(2);
        comment.setCreatedDate(LocalDateTime.of(2016, Month.SEPTEMBER, 15, 15, 30, 40));
        comments.add(comment);

        comment = new Comment("Comment text 3", 3, 1);
        comment.setCommentId(3);
        comment.setCreatedDate(LocalDateTime.of(2017, Month.SEPTEMBER, 20, 20, 40, 50));
        comments.add(comment);

        // When
        for (int i=0; i< comments.size(); i++){
            commentService.create(comments.get(i));
        }
        List<Comment> commentsActual = commentService.getAll();

        // Then
        assertEquals(comments.size(), commentsActual.size());
        for (int i = 0; i < commentsActual.size(); i++) {
            assertEquals(comments.get(i).getCommentId(), commentsActual.get(i).getCommentId());
            assertEquals(comments.get(i).getCommentText(), commentsActual.get(i).getCommentText());
            assertEquals(comments.get(i).getCreatedDate(), commentsActual.get(i).getCreatedDate());
            assertEquals(comments.get(i).getTaskId(), commentsActual.get(i).getTaskId());
            assertEquals(comments.get(i).getUserId(), commentsActual.get(i).getUserId());
            commentService.delete(commentsActual.get(i).getCommentId());
        }
        commentsActual = commentService.getAll();
        assertEquals(0, commentsActual.size());

    }

}
