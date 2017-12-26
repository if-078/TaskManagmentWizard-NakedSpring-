package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.TagDaoInterface;
import com.softserve.academy.tmw.dao.mapper.TagMapper;
import com.softserve.academy.tmw.entity.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class TagDao extends EntityDao<Tag> implements TagDaoInterface {

  public TagDao(@Value("${tag}") String table) {
    super(table, new TagMapper());
  }

  @Override
  public Tag create(Tag entity) {
    String sql = "INSERT INTO " + table
        + " (name, user_id, project_id) VALUES (:name, :user_id, :project_id)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(sql, getParameters(entity), keyHolder);
    entity.setId(keyHolder.getKey().intValue());
    return entity;
  }

  @Override
  public boolean update(Tag entity) {
    String sql = "UPDATE " + table
        + " SET name = :name, user_id = :user_id, project_id = :project_id WHERE id = :id";
    return jdbcTemplate.update(sql, getParameters(entity)) == 1;
  }

  @Override
  public boolean deleteAllByProject(int projectId) {
    String Sql = "DELETE FROM " + table + " WHERE project_id = :project_id";
    return jdbcTemplate.update(Sql, new MapSqlParameterSource("project_id", projectId)) > 0;
  }

  @Override
  public boolean deleteTagsOfTask(int taskId) {
    String Sql = "DELETE FROM " + "tmw.tags_tasks" + " WHERE task_id = :task_id";
    return jdbcTemplate.update(Sql, new MapSqlParameterSource("task_id", taskId)) > 0;
  }

  @Override
  public List<Tag> getAllByProject(int projectId) {
    String sql = "SELECT * FROM " + table + " WHERE project_id = :project_id";
    List<Tag> list =
        jdbcTemplate
            .query(sql, new MapSqlParameterSource("project_id", projectId), new TagMapper());
    return list;
  }

  @Override
  public List<Tag> getAllByTaskId(int taskId) {
    String sql = "SELECT tag.id, tag.name, tag.user_id,  project_id FROM tag\n"
        + "JOIN tags_tasks ON tag.id = tags_tasks.tag_id\n"
        + "WHERE tags_tasks.task_id=:taskId;";
    List<Tag> list =
        jdbcTemplate.query(sql, new MapSqlParameterSource("taskId", taskId), new TagMapper());
    return list;
  }


  @Override
  public boolean setTagsToTask(List<Tag> tags, int taskId) {
    if (tags.size() == 0) return false;
    MapSqlParameterSource param = new MapSqlParameterSource();
    StringBuilder sql = new StringBuilder("INSERT into tmw.tags_tasks (tag_id, task_id) VALUES ");
    param.addValue("task", taskId);
    for (int i = 0; i < tags.size(); i++) {
      String tag = "tag" + i;
      sql.append("(:" + tag + ", :task),");
      param.addValue(tag, tags.get(i).getId());
    }
    sql.setLength(sql.length() - 1);
    return jdbcTemplate.update(sql.toString(), param) > 0;
  }

  private MapSqlParameterSource getParameters(Tag entity) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("id", entity.getId());
    param.addValue("name", entity.getName());
    param.addValue("user_id", entity.getUserId());
    param.addValue("project_id", entity.getProjectId());
    return param;
  }

}