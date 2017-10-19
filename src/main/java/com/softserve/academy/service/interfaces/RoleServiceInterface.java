package com.softserve.academy.service.interfaces;

import com.softserve.academy.dao.interfaces.EntityDaoInterface;
import com.softserve.academy.entity.Role;

import java.util.List;

public interface RoleServiceInterface extends EntityDaoInterface<Role> {
    List<Role> addBatch(Role... roles);

    boolean deleteAll();
}
