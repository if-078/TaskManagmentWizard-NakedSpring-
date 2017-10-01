package com.softserve.if078.tmwSpring.services;

import com.softserve.if078.tmwSpring.entities.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {

	List<Role> getAll()throws SQLException;

	Role get(Integer id)throws SQLException;

	boolean update(Role entity)throws SQLException;

	boolean delete(int id)throws SQLException;

	boolean deleteAll() throws SQLException;

	Role create(Role entity)throws SQLException;

	List<Role> create(Role... roles) throws SQLException;

}
