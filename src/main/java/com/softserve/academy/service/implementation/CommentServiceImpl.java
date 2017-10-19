package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.CommentDao;
import com.softserve.academy.entity.Comment;
import com.softserve.academy.service.interfaces.EntityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements EntityServiceInterface<Comment> {

    @Autowired
    CommentDao dao;

    @Override
    public List getAll() {
        return dao.getAll();
    }

    @Override
    public Comment findOne(int id) {
        return dao.findOne(id);
    }

    @Override
    public boolean update(Comment entity) {
        return dao.update(entity);
    }

    @Override
    public boolean delete(int id) {
        return dao.delete(id);
    }

    @Override
    public Comment create(Comment entity) {
        return dao.create(entity);
    }

}
