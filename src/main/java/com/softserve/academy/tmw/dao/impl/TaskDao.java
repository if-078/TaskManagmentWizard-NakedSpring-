package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.TaskDaoInterface;
import com.softserve.academy.tmw.dao.mapper.*;
import com.softserve.academy.tmw.dao.util.JooqSQLBuilder;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.entity.Task;
import java.util.Date;
import java.util.List;
import org.jooq.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
@PropertySource("classpath:tables.properties")
public class TaskDao extends EntityDao<Task> implements TaskDaoInterface {

  public TaskDao(@Value("${task}") String table) {
    super(table, new TaskMapper());
  }


  @Override
  public Task findOne(int id) {
    String query = "SELECT * FROM " + table + " WHERE id = :id";
    Task task = jdbcTemplate
        .queryForObject(query, new MapSqlParameterSource("id", id), new TaskMapper());
    return task;
  }

  @Override
  public boolean update(Task task) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    java.sql.Timestamp start, end, planning;
    Date date = new Date();

    if (task.getStartDate() == null) {
      start = new java.sql.Timestamp(date.getTime());
    } else {
      start = new java.sql.Timestamp(task.getStartDate().getTime());
    }

    if (task.getPlanningDate() == null) {
      planning = null;
    } else {
      planning = new java.sql.Timestamp(task.getPlanningDate().getTime());
    }

    if (task.getEndDate() == null) {
      end = new java.sql.Timestamp(date.getTime());
    } else {
      end = new java.sql.Timestamp(task.getEndDate().getTime());
    }

    if (task.getStatusId() == 0) {
      task.setStatusId(1);
    }
    if (task.getPriorityId() == 0) {
      task.setPriorityId(1);
    }

    String sql =
        "UPDATE " + table
            + " SET name=:name, created_date=:created_date, planning_date=:planning_date, start_date=:start_date, "
            + "end_date=:end_date, estimate_time=:estimate_time, assign_to=:assign_to, status_id=:status_id, "
            + "priority_id=:priority_id, parent_id=:parent_id WHERE id=:id";

    param.addValue("name", task.getName());
    param.addValue("created_date", task.getCreatedDate());
    param.addValue("planning_date", planning);
    param.addValue("start_date", start);
    param.addValue("end_date", end);
    param.addValue("estimate_time", task.getEstimateTime());
    param.addValue("assign_to", task.getAssignTo());
    param.addValue("status_id", task.getStatusId());
    param.addValue("priority_id", task.getPriorityId());
    param.addValue("parent_id", task.getParentId());
    param.addValue("id", task.getId());

    return jdbcTemplate.update(sql, param) >= 1;
  }

  @Override
  public Task create(Task task) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();

    java.sql.Timestamp created, start, end;
    Date date = new Date();

    created = new java.sql.Timestamp(date.getTime());

    if (task.getStartDate() == null) {
      start = null;
    } else {
      start = new java.sql.Timestamp(task.getStartDate().getTime());
    }

    if (task.getEndDate() == null) {
      end = null;
    } else {
      end = new java.sql.Timestamp(task.getEndDate().getTime());
    }

    if (task.getStatusId() == 0) {
      task.setStatusId(1);
    }
    if (task.getPriorityId() == 0) {
      task.setPriorityId(1);
    }

    String sql = "INSERT INTO " + table
        + " (name, created_date, planning_date, start_date, end_date, estimate_time, assign_to, status_id, "
        + "priority_id, parent_id) VALUES (:name, :created_date, :planning_date, :start_date, :end_date, :estimate_time, "
        + ":assign_to, :status_id, :priority_id, :parent_id)";

    param.addValue("name", task.getName());
    param.addValue("created_date", created);
    param.addValue("planning_date", task.getPlanningDate());
    param.addValue("start_date", start);
    param.addValue("end_date", end);
    param.addValue("estimate_time", task.getEstimateTime());
    param.addValue("assign_to", task.getAssignTo());
    param.addValue("status_id", task.getStatusId());
    param.addValue("priority_id", task.getPriorityId());
    param.addValue("parent_id", task.getParentId());
    param.addValue("id", task.getId());

    jdbcTemplate.update(sql, param, keyHolder);
    task.setId(keyHolder.getKey().intValue());

    return task;
  }

  @Override
  public List<Tag> getTagsOfTask(int taskId) {
    String sql = "SELECT tag.id, tag.name, tag.user_id FROM tmw.tag INNER JOIN tmw.tags_tasks  "
        + "ON tag.id=tags_tasks.tag_id  WHERE tags_tasks.task_id = :tags_tasks.task_id";

    List<Tag> tags = jdbcTemplate.query(sql,
        new MapSqlParameterSource("tags_tasks.task_id", taskId), new TagMapper());

    return tags;
  }

  @Override
  public List<Comment> getCommentsOfTask(int taskId) {
    String sql = "SELECT id, comment_text, created_date, task_id, user_id FROM tmw.comment "
        + "WHERE task_id = :task_id";
    List<Comment> comments =
        jdbcTemplate.query(sql, new MapSqlParameterSource("task_id", taskId), new CommentMapper());

    return comments;
  }

  @Override
  public List<Task> getSubtasks(int id) {
    String query =
        "SELECT id, name, created_date, planning_date, start_date, end_date, estimate_time, "
            + "assign_to, status_id, priority_id, parent_id FROM task WHERE parent_id = :parent_id";
    List<Task> tasks =
        jdbcTemplate.query(query, new MapSqlParameterSource("parent_id", id), new TaskMapper());

    return tasks;
  }

  @Override
  public List<Task> getTasksAssignToUser(int userId) {
    String query =
        "SELECT id, name, created_date, planning_date, start_date, end_date, estimate_time, " +
            "assign_to, status_id, priority_id, parent_id FROM task "
            + ""
            + "WHERE assign_to= :assign_to";

    List<Task> tasks =
        jdbcTemplate.query(query, new MapSqlParameterSource("assign_to", userId), new TaskMapper());

    return tasks;
  }


  @Override
  public List<TaskTableDTO> getFilteredTasks(JooqSQLBuilder builder) {
    Select select = builder.buildSql();
    String query = select.getSQL();
    List<TaskTableDTO> tasks = jdbcTemplate.query(query, new TaskMapperForTable());

    return tasks;
  }

  @Override
  public List<Task> getPlannedTasks() {
    String sql = "SELECT * FROM " + table + " WHERE planning_date != ''";
    List<Task> tasks = jdbcTemplate.query(sql, new TaskMapper());
    return tasks;
  }

  public TaskFullInfoDTO getFullTask(int id) {
    String query =
        "SELECT task.id, task.name, task.created_date, task.planning_date, task.start_date, task.end_date, task.estimate_time,\n"
            + "  user.name as user, status.name as status, priority.name as priority, task.parent_id\n"
            + "  FROM task\n"
            + "  LEFT JOIN priority ON task.priority_id = priority.id\n"
            + "  LEFT JOIN status ON task.status_id = status.id\n"
            + "  LEFT JOIN user ON task.assign_to = user.id"
            + "WHERE task.id=:id";
    return null;
  }

  @Override
  public List<TaskTreeDTO> findTaskByTree(int id) {
    String query = "SELECT id, name, (SELECT COUNT(*) FROM task WHERE parent_id = t.id) count_children " +
            "FROM task AS t WHERE parent_id = :parent_id";
    List<TaskTreeDTO> tasks = jdbcTemplate.query(query, new MapSqlParameterSource("parent_id", id), new TaskMapperForTree());
    System.out.println(tasks);
    return tasks;
  }

}