package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.CommentDao;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements EntityServiceInterface<Comment> {

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

}