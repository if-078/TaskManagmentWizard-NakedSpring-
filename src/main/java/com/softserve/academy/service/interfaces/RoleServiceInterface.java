package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.Role;

import java.util.List;
import com.softserve.academy.dao.interfaces.EntityDaoInterface;

public interface RoleServiceInterface extends EntityDaoInterface<Role> {
	List<Role> addBatch(Role... roles);

	boolean deleteAll();
}
