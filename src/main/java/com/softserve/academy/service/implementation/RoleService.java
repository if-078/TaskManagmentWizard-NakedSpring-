package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.RoleDao;
import com.softserve.academy.entity.Role;
import com.softserve.academy.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class RoleService implements Service<Role> {

  @Autowired
  RoleDao dao;

  @Override
  public List<Role> getAll() {
    return dao.getAll();
  }

  @Override
  public Role findOne(int id) {
    return dao.findOne(id);
  }

  @Override
  public boolean update(Role role) {
    return dao.update(role);
  }

  @Override
  public boolean delete(int id) {
    return dao.delete(id);
  }

  public boolean deleteAll() {
    return dao.deleteAll();
  }

  @Override
  public Role create(Role role) {
    return dao.create(role);
  }

  public List<Role> addBatch(Role...roles) {
    return dao.addBatch(roles);
  }
}
