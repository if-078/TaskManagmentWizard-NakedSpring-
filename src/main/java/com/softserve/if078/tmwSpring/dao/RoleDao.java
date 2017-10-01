package com.softserve.if078.tmwSpring.dao;

import com.softserve.if078.tmwSpring.entities.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao extends DaoInterface<Role> {

	List<Role> create(Role... roles) throws SQLException;

	boolean deleteAll() throws SQLException;
}
