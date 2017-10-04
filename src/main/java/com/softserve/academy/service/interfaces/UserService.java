package com.softserve.academy.service.interfaces;

import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.User;

public interface UserService extends EntityDao<User> {
	User findByEmail(String email);
}
