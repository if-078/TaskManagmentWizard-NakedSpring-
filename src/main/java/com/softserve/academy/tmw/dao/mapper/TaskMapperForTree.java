package com.softserve.academy.tmw.dao.mapper;


import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapperForTree implements RowMapper<TaskTreeDTO> {

  @Override
  public TaskTreeDTO mapRow(ResultSet resultSet, int i) throws SQLException {
    TaskTreeDTO dto = new TaskTreeDTO();
    dto.setId(resultSet.getInt("id"));
    dto.setText(resultSet.getString("name"));
    dto.setChildren(resultSet.getInt("count_children")!=0);
    return dto;
  }
}
