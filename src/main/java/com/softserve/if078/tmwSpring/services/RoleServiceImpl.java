package com.softserve.if078.tmwSpring.services;

import com.softserve.if078.tmwSpring.dao.RoleDaoImpl;
import com.softserve.if078.tmwSpring.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDaoImpl roleDao;

	@Override
	public List<Role> getAll()throws SQLException {
		return roleDao.getAll();
	}

	@Override
	public Role get(Integer id)throws SQLException {
		return roleDao.findOne(id);
	}

	@Override
	public boolean update(Role role)throws SQLException {
		return roleDao.update(role);
	}

	@Override
	public boolean delete(int id)throws SQLException {
		return roleDao.delete(id);
	}

	@Override
	public boolean deleteAll() throws SQLException {
		return roleDao.deleteAll();
	}

	@Override
	public Role create(Role role)throws SQLException {
		return roleDao.create(role);
	}

	@Override
	public List<Role> create(Role... roles) throws SQLException {
		return roleDao.create(roles);
	}
}
