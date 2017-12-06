package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.service.api.CommentServiceInterface;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements CommentServiceInterface {

  @Autowired
  CommentDao dao;

  @Override
  public List getAll() {
    return dao.getAll();
  }

  @Override
  public Comment findOne(int id) {
    return dao.findOne(id);
  }

  @Override
  public boolean update(Comment entity) {
    return dao.update(entity);
  }

  @Override
  public boolean delete(int id) {
    return dao.delete(id);
  }

  @Override
  public Comment create(Comment entity) {
    return dao.create(entity);
  }

  @Override
  public boolean setCommentsToTask(List<Comment> comments, int taskId) {
    return dao.setCommentsToTask(comments, taskId);
  }

  @Override
  public boolean deleteCommentsOfTask(int taskId) {
    return dao.deleteCommentsOfTask(taskId);
  }

  @Override
  public List<Comment> getCommentsByTaskId(int taskId) {
    return dao.getCommentsByTaskId(taskId);
  }
}