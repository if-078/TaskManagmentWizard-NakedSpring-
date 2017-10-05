package com.softserve.academy.dao.interfaces;


import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.User;

public interface UserDao extends EntityDao<User> {
	User findByEmail(String email);
}
