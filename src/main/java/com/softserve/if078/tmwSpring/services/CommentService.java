package com.softserve.if078.tmwSpring.services;

import java.sql.SQLException;
import java.util.List;

import com.softserve.if078.tmwSpring.entities.Comment;

public interface CommentService {

    List<Comment> getAll()throws SQLException;

    Comment get(int id)throws SQLException;

    boolean update(Comment comment)throws SQLException;

    boolean delete(int id)throws SQLException;

    Comment create(Comment comment)throws SQLException;

}
