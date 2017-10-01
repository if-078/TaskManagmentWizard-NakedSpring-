package com.softserve.if078.tmwSpring.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.if078.tmwSpring.dao.CommentDaoImpl;
import com.softserve.if078.tmwSpring.entities.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDaoImpl commentDao;

    @Override
    public List<Comment> getAll()throws SQLException {
        return commentDao.getAll();
    }

    @Override
    public Comment get(int id)throws SQLException {
        return commentDao.findOne(id);
    }

    @Override
    public boolean update(Comment comment)throws SQLException {
        return commentDao.update(comment);
    }

    @Override
    public boolean delete(int id)throws SQLException {
        return commentDao.delete(id);

    }

    @Override
    public Comment create(Comment comment)throws SQLException {
        return commentDao.create(comment);

    }
}