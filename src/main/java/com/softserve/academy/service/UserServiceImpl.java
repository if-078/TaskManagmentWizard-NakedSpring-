package com.softserve.academy.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	
	

	@Override
	public List getAll() throws SQLException {
	
		return null;
	}

	@Override
	public User findOne(int id) throws SQLException {
		return null;
	}

	@Override
	public boolean update(User user) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User create(User user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
