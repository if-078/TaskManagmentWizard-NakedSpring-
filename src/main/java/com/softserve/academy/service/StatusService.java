package com.softserve.academy.service;

import com.softserve.academy.dao.DaoInterface;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StatusService implements ServiceInterface<Status> {
    @Autowired
    private DaoInterface<Status> statusDao;

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
