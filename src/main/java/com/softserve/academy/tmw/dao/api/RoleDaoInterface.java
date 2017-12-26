package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.entity.Role;
import java.util.List;

public interface RoleDaoInterface extends EntityDaoInterface<Role> {

  List<Role> addBatch(Role... roles);

  boolean deleteAll();

}