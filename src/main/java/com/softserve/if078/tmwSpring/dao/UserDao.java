package com.softserve.if078.tmwSpring.dao;

import java.sql.SQLException;

import com.softserve.if078.tmwSpring.entities.User;

public interface UserDao extends DaoInterface<User> {

	User findByEmail(String email) throws SQLException;
}
