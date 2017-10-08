package com.softserve.academy.service.interfaces;

import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.Role;

import java.util.List;

public interface RoleService extends EntityDao<Role> {
	List<Role> addBatch(Role... roles);

	boolean deleteAll();
}
