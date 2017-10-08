package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class StatusService implements Service<Status> {

  @Autowired
  StatusDao statusDao;


  @Override
  public List<Status> getAll() {
    return statusDao.getAll();
  }

  @Override
  public Status findOne(int id) {
    return statusDao.findOne(id);
  }

  @Override
  public boolean update(Status entity) {
    return statusDao.update(entity);
  }

  @Override
  public boolean delete(int id) {
    return statusDao.delete(id);
  }

  @Override
  public Status create(Status entity) {
    return statusDao.create(entity);
  }
}
