package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.CommentDaoInterface;
import com.softserve.academy.tmw.dao.mapper.CommentMapper;
import com.softserve.academy.tmw.entity.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class CommentDao extends EntityDao<Comment> implements CommentDaoInterface {

  public CommentDao(@Value("${comment}") String table) {
    super(table, new CommentMapper());
  }

  @Override
  public Comment create(Comment entity) {
    String sql = "INSERT INTO " + table
        + " (comment_text, created_date, task_id, user_id) VALUES (:comment_text, :created_date, :task_id, :user_id)";
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

  @Override
  public boolean deleteCommentsOfTask(int taskId) {
    String Sql = "DELETE FROM tmw.comment WHERE task_id = :task_id";
    return jdbcTemplate.update(Sql, new MapSqlParameterSource("task_id", taskId)) > 0;

  }

  @Override
  public boolean setCommentsToTask(List<Comment> comments, int taskId) {
    if (comments.size() == 0) {
      return false;
    }
    MapSqlParameterSource param = new MapSqlParameterSource();
    StringBuilder sql = new StringBuilder(
        "INSERT into tmw.comment (comment_text, created_date, task_id, user_id) VALUES ");
    for (int i = 0; i < comments.size(); i++) {
      String text = "text" + i;
      String date = "date" + i;
      String task = "task" + i;
      String user = "user" + i;
      sql.append("(:" + text + ", :" + date + ", :" + task + ", :" + user + "),");
      param.addValue(text, comments.get(i).getCommentText());
      param.addValue(date, comments.get(i).getCreatedDate());
      param.addValue(task, (taskId == 0) ? comments.get(i).getTaskId() : taskId);
      param.addValue(user, comments.get(i).getUserId());
    }
    sql.setLength(sql.length() - 1);
    return jdbcTemplate.update(sql.toString(), param) > 0;

  }

  @Override
  public List<Comment> getCommentsByTaskId(int taskId) {
    String sql =
        "SELECT c.*, u.name FROM " + table
            + " as c join tmw.user as u on c.user_id = u.id WHERE task_id = :taskId";
    List<Comment> list =
        jdbcTemplate.query(sql, new MapSqlParameterSource("taskId", taskId), new CommentMapper());
    return list;
  }


}
