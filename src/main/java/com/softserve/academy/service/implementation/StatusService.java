package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.softserve.academy.service.interfaces.EntityServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class StatusService implements EntityServiceInterface<Status> {

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

  public Status findByName(Status target) {
    for (Status status : getAll()) {
      if (status.getName().equals(target.getName()))
        return status;
    }
    return null;
  }
}
