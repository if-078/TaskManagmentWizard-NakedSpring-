
package com.softserve.academy.dao.interfaces;

import com.softserve.academy.entity.Role;
import com.softserve.academy.entity.Tag;

import java.util.List;

public interface RoleDaoInterface extends EntityDao<Role> {
  List<Role> addBatch(Role... roles);

  boolean deleteAll();

}
