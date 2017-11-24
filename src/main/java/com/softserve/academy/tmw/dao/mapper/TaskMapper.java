package com.softserve.academy.tmw.dao.mapper;

import com.softserve.academy.tmw.entity.Task;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements RowMapper<Task> {

  @Override
  public Task mapRow(ResultSet rs, int i) throws SQLException {
    Task task = new Task(rs.getInt(1), rs.getString(2), rs.getDate(3),
        rs.getTimestamp(4), rs.getDate(5), rs.getDate(6), rs.getInt(7),
        rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));

    return task;
  }
}
