package com.softserve.academy.dao.mapper;

import com.softserve.academy.entity.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskMapper implements RowMapper<Task> {

  @Override
  public Task mapRow(ResultSet rs, int i) throws SQLException {
    Task task = new Task(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getDate(5),
        rs.getTime(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));

    return task;
  }
}
