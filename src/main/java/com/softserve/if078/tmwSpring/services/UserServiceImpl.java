package com.softserve.if078.tmwSpring.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.if078.tmwSpring.dao.UserDao;
import com.softserve.if078.tmwSpring.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<User> getAll() throws SQLException {
		return userDao.getAll();
	}

	@Override
	public User get(Integer id) throws SQLException {
		return userDao.findOne(id);
	}

	@Override
	public boolean update(User entity) throws SQLException {
		return userDao.update(entity);
	}

	@Override
	public boolean delete(Integer id) throws SQLException {
		return userDao.delete(id);

	}

	@Override
	public User create(User entity) throws SQLException {
		return userDao.create(entity);

	}

	@Override
	public User findByEmail(String email) throws SQLException {
		return userDao.findByEmail(email);
	}

}
