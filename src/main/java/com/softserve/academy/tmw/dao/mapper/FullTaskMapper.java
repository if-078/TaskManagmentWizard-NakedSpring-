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
  public Task mapRow(ResultSet resultSet, int i) throws SQLException {
    Task task = new Task();
    Priority priority = new Priority(resultSet.getInt("priority_id"),
        resultSet.getString("priority_name"));
    task.setPriority(priority);
    Status status = new Status(resultSet.getInt("status_id"),
        resultSet.getString("status_name"));
    task.setStatus(status);
    return null;
  }
}
