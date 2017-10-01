package com.softserve.if078.tmwSpring.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.softserve.if078.tmwSpring.entities.Comment;

@Component
public class CommentDaoImpl implements CommentDao {

    private final String tabName = "tmw.comment";

    @Qualifier("dataSource")
    @Autowired
    DataSource datasource;

    @Override
    public List<Comment> getAll()throws SQLException {
        String sql = "SELECT * FROM " + tabName;
        List comments = new ArrayList<>();
        try (Statement stmt = datasource.getConnection().createStatement();
             ResultSet resultSet = stmt.executeQuery(sql);) {
            while (resultSet.next()) {
                Comment comment = extractCommentFromResultSet(resultSet);
                comments.add(comment);
            }
        }
        return comments;
    }

    @Override
    public Comment findOne(int id) throws SQLException {
        String sql = "SELECT * FROM "+ tabName + " WHERE comment_id=" + id;
        try (Statement stmt = datasource.getConnection().createStatement();
             ResultSet resultSet = stmt.executeQuery(sql);) {
            return resultSet.next()? extractCommentFromResultSet(resultSet) : null;
        }
    }

    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        comment.setCommentId(resultSet.getInt(1));
        comment.setCommentText(resultSet.getString(2));
        comment.setCreatedDate(resultSet.getTimestamp(3).toLocalDateTime());
        comment.setTaskId(resultSet.getInt(4));
        comment.setUserId(resultSet.getInt(5));
        return comment;
    }

    @Override
    public boolean update(Comment comment) throws SQLException{
        int countUpdate = 0;
        String sql = "UPDATE "+tabName+" SET comment=? WHERE comment_id=?";
        try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql);) {
            ps.setString(1, comment.getCommentText());
            ps.setInt(2, comment.getCommentId());
            countUpdate = ps.executeUpdate();
        }

        return countUpdate == 1 ? true : false;
    }

    @Override
    public boolean delete(int id)throws SQLException {
        int countUpdate = 0;
        String sql = "DELETE FROM "+tabName+" WHERE comment_id=" + id;
        try (Statement stmt = datasource.getConnection().createStatement();) {
            countUpdate = stmt.executeUpdate(sql);
        }

        return countUpdate == 1;
    }

    @Override
    public Comment create(Comment comment)throws SQLException {

        String sql = "INSERT INTO "+tabName+" (comment, created_date, task_id, user_id)  VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, comment.getCommentText());
            ps.setTimestamp(2, Timestamp.valueOf(comment.getCreatedDate()));
            ps.setInt(3, comment.getTaskId());
            ps.setInt(4, comment.getUserId());
            ps.executeUpdate();

            ResultSet rsI = ps.getGeneratedKeys();
            if(rsI.next()) {
                comment.setCommentId(rsI.getInt(1));
            }

        }
        return comment;
    }

}
