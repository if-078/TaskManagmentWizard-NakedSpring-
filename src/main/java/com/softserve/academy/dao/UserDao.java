package com.softserve.academy.dao;


import com.softserve.academy.entity.User;

public interface UserDao extends DaoInterface<User> {
	User findByEmail(String email);
}
