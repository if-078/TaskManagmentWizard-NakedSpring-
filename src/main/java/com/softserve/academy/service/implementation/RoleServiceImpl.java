package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.RoleDao;
import com.softserve.academy.dao.interfaces.RoleDaoInterface;
import com.softserve.academy.entity.Role;
import com.softserve.academy.service.interfaces.RoleService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  RoleDaoInterface dao;

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
