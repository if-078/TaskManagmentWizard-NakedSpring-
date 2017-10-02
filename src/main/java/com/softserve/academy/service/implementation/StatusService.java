package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.interfaces.EntityDao;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

@org.springframework.stereotype.Service
public class StatusService implements Service<Status> {
    @Autowired
    private EntityDao<Status> statusDao;

    @Override
    public List<Status> getAll() throws SQLException {
        return statusDao.getAll();
    }

    @Override
    public Status findOne(int id) throws SQLException {
        return statusDao.findOne(id);
    }

    @Override
    public boolean update(Status entity) throws SQLException {
        return statusDao.update(entity);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return statusDao.delete(id);
    }

    @Override
    public Status create(Status entity) throws SQLException {
        return statusDao.create(entity);
    }
}
