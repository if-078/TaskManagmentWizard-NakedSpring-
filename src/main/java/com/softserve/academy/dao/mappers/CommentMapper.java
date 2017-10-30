package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setCommentText(rs.getString("comment"));
        comment.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        comment.setTaskId(rs.getInt("task_id"));
        comment.setUserId(rs.getInt("user_id"));
        return comment;
    }

}