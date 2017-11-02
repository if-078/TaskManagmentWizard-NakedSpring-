package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.PriorityDao;
import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class PriorityService implements EntityServiceInterface<Priority> {

  @Autowired
  PriorityDao priorityDao;


  @Override
  public List<Priority> getAll() {
    return priorityDao.getAll();
  }

  @Override
  public Priority findOne(int id) {
    return priorityDao.findOne(id);
  }

  @Override
  public boolean update(Priority entity) {
    return priorityDao.update(entity);
  }

  @Override
  public boolean delete(int id) {
    return priorityDao.delete(id);
  }

  @Override
  public Priority create(Priority entity) {
    return priorityDao.create(entity);
  }
}