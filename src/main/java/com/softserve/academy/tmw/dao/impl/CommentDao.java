package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.mapper.CommentMapper;
import com.softserve.academy.tmw.entity.Comment;
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
public class CommentDao extends EntityDao<Comment> {

    public CommentDao(@Value("${comment}") String table) {
        super(table, new CommentMapper());
    }

    @Override
    public Comment create(Comment entity) {
        String sql = "INSERT INTO " + table + " (comment_text, created_date, task_id, user_id) VALUES (:comment_text, :created_date, :task_id, :user_id)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        param.addValue("comment_text", entity.getCommentText());
        param.addValue("created_date", entity.getCreatedDate());
        param.addValue("task_id", entity.getTaskId());
        param.addValue("user_id", entity.getUserId());
        jdbcTemplate.update(sql, param, keyHolder);
        entity.setId(keyHolder.getKey().intValue());

        return entity;
    }

    @Override
    public boolean update(Comment entity) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        String sql =
                "UPDATE " + table + " SET comment = :comment WHERE id = :id";
        param.addValue("comment_text", entity.getCommentText());
        param.addValue("id", entity.getId());

        return jdbcTemplate.update(sql, param) == 1;

    }

    @Autowired
    public void setTable(@Value("${comment}") String table) {
        this.table = table;
    }

    @Autowired
    public void setMapper(RowMapper<Comment> mapper) {
        this.mapper = mapper;
    }


}
