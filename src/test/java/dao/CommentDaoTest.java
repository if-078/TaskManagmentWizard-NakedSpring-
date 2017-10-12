package dao;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import com.softserve.academy.entity.Comment;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.CommentServiceInterface;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utility.Populator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CommentDaoTest {

  @Autowired
  public CommentServiceInterface commentService;
  @Autowired
  public Populator<User> populatorUser;
  @Autowired
  public Populator<Task> populatorTask;

  @Before
  public void createFewUsers() {
    populatorUser.createDefaultEntity();
    populatorTask.createDefaultEntity();

  }

  @Test
  public void iTShouldCreateAndGetOneComment() throws Exception {
  // Given
  Comment comment = new Comment("Text comment 1", 1, 1);

  comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 10, 10, 20, 30));
  // When
  int id = commentService.create(comment).getId();
  comment.setId(id);

  Comment commentActual = commentService.findOne(id);
  // Then
  assertEquals(comment.getId(), commentActual.getId());
  assertEquals(comment.getCommentText(), commentActual.getCommentText());
  assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
  assertEquals(comment.getTaskId(), commentActual.getTaskId());
  assertEquals(comment.getUserId(), commentActual.getUserId());
  commentService.delete(id);
  }

  @Test
  public void iTShouldCreateAndUpdateOneComment() throws Exception {
  // Given
  Comment comment = new Comment("Text comment", 1, 1);
  comment.setCreatedDate(LocalDateTime.of(2015, Month.SEPTEMBER, 20, 10, 20, 30));
  // When
  int id = commentService.create(comment).getId();
  comment.setId(id);

  comment.setCommentText("Text comment update");
  commentService.update(comment);
  Comment commentActual = commentService.findOne(id);
  // Then
  assertEquals(comment.getId(), commentActual.getId());
  assertEquals(comment.getCommentText(), commentActual.getCommentText());
  assertEquals(comment.getCreatedDate(), commentActual.getCreatedDate());
  assertEquals(comment.getTaskId(), commentActual.getTaskId());
  assertEquals(comment.getUserId(), commentActual.getUserId());
  commentService.delete(id);
  }

  @Test
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
  commentService.create(comments.get(i));
  }
  List<Comment> commentsActual = commentService.getAll();

  // Then
  assertEquals(comments.size(), commentsActual.size());
  for (int i = 0; i < commentsActual.size(); i++) {
  assertEquals(comments.get(i).getId(), commentsActual.get(i).getId());
  assertEquals(comments.get(i).getCommentText(), commentsActual.get(i).getCommentText());
  assertEquals(comments.get(i).getCreatedDate(), commentsActual.get(i).getCreatedDate());
  assertEquals(comments.get(i).getTaskId(), commentsActual.get(i).getTaskId());
  assertEquals(comments.get(i).getUserId(), commentsActual.get(i).getUserId());
  commentService.delete(commentsActual.get(i).getId());
  }
  commentsActual = commentService.getAll();
  assertEquals(0, commentsActual.size());

  }

}
