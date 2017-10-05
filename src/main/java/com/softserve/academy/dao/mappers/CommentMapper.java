package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setCommentText(rs.getString("commentText"));
        comment.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime());
        comment.setTaskId(rs.getInt("taskId"));
        comment.setUserId(rs.getInt("userId"));
        return comment;
    }

}