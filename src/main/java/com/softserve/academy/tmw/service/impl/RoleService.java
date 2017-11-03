package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.api.RoleDaoInterface;
import com.softserve.academy.tmw.entity.Role;
import com.softserve.academy.tmw.service.api.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceInterface {
    @Autowired
    RoleDaoInterface dao;

    @Override
    public List<Role> getAll() {
        return dao.getAll();
    }

    @Override
    public Role findOne(int id) {
        try {
            return dao.findOne(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new Role();
        }
    }

    @Override
    public boolean update(Role role) {
        return dao.update(role);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    public boolean deleteAll() {
        return dao.deleteAll();
    }

    @Override
    public Role create(Role role) {
        return dao.create(role);
    }

    public List<Role> addBatch(Role... roles) {
        return dao.addBatch(roles);
    }
}
