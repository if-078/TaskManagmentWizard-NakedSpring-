package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class StatusService implements Service<Status> {

  @Override
  public List<Status> getAll() {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public Status findOne(int id) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public boolean update(Status entity) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public boolean delete(int id) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public Status create(Status entity) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }
  //
  // private EntityDao<Status> statusDao;
  //
  // @Autowired
  // public void setStatusDao(EntityDao<Status> statusDao) {
  // this.statusDao = statusDao;
  // }
  //
  // @Override
  // public List<Status> getAll() {
  // return statusDao.getAll();
  // }
  //
  // @Override
  // public Status findOne(int id) {
  // return statusDao.findOne(id);
  // }
  //
  // @Override
  // public boolean update(Status entity) {
  // return statusDao.update(entity);
  // }
  //
  // @Override
  // public boolean delete(int id) {
  // return statusDao.delete(id);
  // }
  //
  // @Override
  // public Status create(Status entity) {
  // return statusDao.create(entity);
  // }
}
