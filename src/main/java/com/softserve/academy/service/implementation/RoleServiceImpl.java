package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.RoleDao;
import com.softserve.academy.dao.interfaces.RoleDaoInterface;
import com.softserve.academy.entity.Role;
<<<<<<< HEAD:src/main/java/com/softserve/academy/service/implementation/RoleServiceImpl.java
import com.softserve.academy.service.interfaces.RoleService;
import com.softserve.academy.service.interfaces.Service;
=======
>>>>>>> d73202f5a22cfc9794d14a1d84c323fe17c4bc46:src/main/java/com/softserve/academy/service/implementation/RoleService.java
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.softserve.academy.service.interfaces.EntityService;

@org.springframework.stereotype.Service
<<<<<<< HEAD:src/main/java/com/softserve/academy/service/implementation/RoleServiceImpl.java
public class RoleServiceImpl implements RoleService {
=======
public class RoleService implements EntityService<Role> {
>>>>>>> d73202f5a22cfc9794d14a1d84c323fe17c4bc46:src/main/java/com/softserve/academy/service/implementation/RoleService.java

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
