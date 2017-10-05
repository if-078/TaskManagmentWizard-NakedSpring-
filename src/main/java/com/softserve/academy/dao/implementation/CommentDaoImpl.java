package com.softserve.academy.dao.implementation;

import com.softserve.academy.entity.Comment;
import com.softserve.academy.entity.User;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class CommentDaoImpl extends Dao<Comment>{

    public CommentDaoImpl() {
    }

    @Override
    public Comment create(Comment entity) {
        String sql = "INSERT INTO " + table + " (comment, created_date, task_id, user_id) VALUES (:commentText, :createdDate, :taskId, :userId)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        param.addValue("commentText", entity.getCommentText());
        param.addValue("createdDate", entity.getCreatedDate());
        param.addValue("taskId", entity.getTaskId());
        param.addValue("userId", entity.getUserId());
        operations.update(sql, param, keyHolder);
        entity.setId(keyHolder.getKey().intValue());

        return entity;
    }

    @Override
    public boolean update(Comment entity) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        String sql =
                "UPDATE " + table + " SET comment = :commentText WHERE id = :id";
        param.addValue("commentText", entity.getCommentText());
        param.addValue("id", entity.getId());

        return operations.update(sql, param) == 1;

    }

    @Autowired
    public void setTable(@Value("${comment}")String table) {
        this.table = table;
    }

    @Autowired
    public void setMapper(RowMapper<Comment> mapper) {
        this.mapper = mapper;
    }


}
