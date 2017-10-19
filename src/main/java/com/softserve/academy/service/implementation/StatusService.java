package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.EntityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements EntityServiceInterface<Status> {

    @Autowired
    StatusDao statusDao;


    @Override
    public List<Status> getAll() {
        return statusDao.getAll();
    }

    @Override
    public Status findOne(int id) {
        return statusDao.findOne(id);
    }

    @Override
    public boolean update(Status entity) {
        return statusDao.update(entity);
    }

    @Override
    public boolean delete(int id) {
        return statusDao.delete(id);
    }

    @Override
    public Status create(Status entity) {
        return statusDao.create(entity);
    }

    public Status findByName(Status target) {
        return getAll().stream().filter(s -> s.getName().contains(target.getName())).findAny().orElse(new Status());
    }
}