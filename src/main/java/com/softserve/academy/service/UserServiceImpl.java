package com.softserve.academy.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.academy.dao.implementation.UserDaoImpl;
import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDaoImpl userDao;
	
	@Override
	public List getAll() throws SQLException {
	    return userDao.getAll();
	}

	@Override
	public User findOne(int id) throws SQLException {
		return userDao.findOne(id);
	}

	@Override
	public boolean update(User user) throws SQLException {
		return userDao.update(user);
	}

	@Override
	public boolean delete(int id) throws SQLException {
		return userDao.delete(id);
	}

	@Override
	public User create(User user) throws SQLException {
		return userDao.create(user);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}
