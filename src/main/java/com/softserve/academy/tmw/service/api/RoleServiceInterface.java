package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.dao.api.EntityDaoInterface;
import com.softserve.academy.tmw.entity.Role;

import java.util.List;

public interface RoleServiceInterface extends EntityDaoInterface<Role> {
    List<Role> addBatch(Role... roles);

    boolean deleteAll();
}
