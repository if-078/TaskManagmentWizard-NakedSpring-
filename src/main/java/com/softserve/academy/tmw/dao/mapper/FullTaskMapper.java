package com.softserve.academy.tmw.dao.mapper;

import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.entity.Status;
import com.softserve.academy.tmw.entity.Task;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Oleg on 07.11.2017.
 */
public class FullTaskMapper implements RowMapper<Task> {

  @Override
  public Task mapRow(ResultSet rs, int i) throws SQLException {
    Task task = new Task(rs.getInt("id"), rs.getString("name"), rs.getDate("created_date"),
        rs.getDate("planning_date"), rs.getDate("start_date"), rs.getDate("end_date"),
        rs.getTime("estimate_Time"), rs.getInt("assign_to"),
        rs.getInt("status_id"), rs.getInt("priority_id"), rs.getInt("parent_id"));

    Priority priority = new Priority(rs.getInt("priority_id"), rs.getString("priority_name"));
    task.setPriority(priority);
    Status status = new Status(rs.getInt("status_id"), rs.getString("status_name"));
    task.setStatus(status);

    return task;
  }
}
