package com.softserve.if078.tmwSpring.services;

import java.sql.SQLException;
import java.util.List;

import com.softserve.if078.tmwSpring.entities.User;

public interface UserService {

	List<User> getAll() throws SQLException;

	User get(Integer id) throws SQLException;

	boolean update(User entity) throws SQLException;

	boolean delete(Integer id) throws SQLException;

	User create(User entity) throws SQLException;

	User findByEmail(String email) throws SQLException;

}
