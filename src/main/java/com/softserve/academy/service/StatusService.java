package com.softserve.academy.service;

import com.softserve.academy.dao.DaoInterface;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StatusService implements ServiceInterface<Status> {
  @Autowired
  private DaoInterface<Status> statusDao;

  @Override
  public List<Status> getAll() {
    try {
      return statusDao.getAll();
    } catch (SQLException ex) {
      Logger.getLogger(StatusService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Status findOne(int id) {
    try {
      return statusDao.findOne(id);
    } catch (SQLException ex) {
      Logger.getLogger(StatusService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public boolean update(Status entity) {
    try {
      return statusDao.update(entity);
    } catch (SQLException ex) {
      Logger.getLogger(StatusService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  @Override
  public boolean delete(int id) {
    try {
      return statusDao.delete(id);
    } catch (SQLException ex) {
      Logger.getLogger(StatusService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
  }

  @Override
  public Status create(Status entity) {
    try {
      return statusDao.create(entity);
    } catch (SQLException ex) {
      Logger.getLogger(StatusService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
