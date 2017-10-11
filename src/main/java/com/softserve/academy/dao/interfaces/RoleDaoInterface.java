package com.softserve.academy.dao.interfaces;

import com.softserve.academy.entity.Role;
import java.util.List;

public interface RoleDaoInterface extends EntityDaoInterface<Role> {
  List<Role> addBatch(Role... roles);

  boolean deleteAll();

}
