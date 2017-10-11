package com.softserve.academy.dao.interfaces;


import com.softserve.academy.entity.User;
import com.softserve.academy.dao.interfaces.EntityDaoInterface;

public interface UserDaoInterface extends EntityDaoInterface<User> {
	User findByEmail(String email);
}
