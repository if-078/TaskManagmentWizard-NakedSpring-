package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.softserve.academy.service.interfaces.EntityService;

@org.springframework.stereotype.Service
public class StatusService implements EntityService<Status> {

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
