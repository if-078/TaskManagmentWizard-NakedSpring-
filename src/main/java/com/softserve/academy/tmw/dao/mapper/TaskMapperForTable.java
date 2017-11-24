package com.softserve.academy.tmw.dao.mapper;


import com.softserve.academy.tmw.dto.TaskTableDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TaskMapperForTable implements RowMapper<TaskTableDTO> {

  @Override
  public TaskTableDTO mapRow(ResultSet resultSet, int i) throws SQLException {
    TaskTableDTO dto = new TaskTableDTO();
    dto.setId(resultSet.getInt("id"));
    dto.setName(resultSet.getString("name"));
    dto.setEstimateTime(resultSet.getString("estimate_time"));
    dto.setPriority(resultSet.getString("priority"));
    dto.setStatus(resultSet.getString("status"));
    dto.setStartDate(resultSet.getDate("start_date"));
    dto.setAssignTo(resultSet.getString("user"));
    return dto;
  }
}
