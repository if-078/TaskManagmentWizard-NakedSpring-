package it.com.softserve.academy.tmw.dao.utility;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentPopulator {

  private TaskPopulator taskPopulator;
  private CommentDao dao;

  @Autowired
  public void setDao(CommentDao dao) {
    this.dao = dao;
  }

  @Autowired
  public void setTaskPopulator(TaskPopulator taskPopulator) {
    this.taskPopulator = taskPopulator;
  }


  public Comment createDefaultEntity() {
    Comment comment = new Comment();
    comment.setCommentText("Default comment text.txt");
    Task task = taskPopulator.createDefaultEntity();
    comment.setUserId(task.getAssignTo());
    comment.setTaskId(task.getId());
    return dao.create(comment);

  }
}