package com.softserve.academy.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.academy.dao.implementation.UserDao;
import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.UserServiceInterface;

@Service
public class UserServiceImpl implements UserServiceInterface {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List getAll() {
	    return userDao.getAll();
	}

	@Override
	public User findOne(int id) {
		return userDao.findOne(id);
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean delete(int id) {
		return userDao.delete(id);
	}

	@Override
	public User create(User user) {
		return userDao.create(user);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
