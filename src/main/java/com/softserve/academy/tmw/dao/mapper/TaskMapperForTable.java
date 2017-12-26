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
    dto.setCreatedDate(resultSet.getDate("created_date"));
    dto.setPlanningDate(resultSet.getTimestamp("planning_date"));
    dto.setStartDate(resultSet.getDate("start_date"));
    dto.setEndDate(resultSet.getDate("end_date"));
    dto.setEstimateTime(resultSet.getInt("estimate_time"));
    dto.setSpentTime(resultSet.getInt("spent_time"));
    dto.setLeftTime(resultSet.getInt("left_time"));
    dto.setAssignTo(resultSet.getInt("assign_to"));
    dto.setPriorityId(resultSet.getInt("priority_id"));
    dto.setStatusId(resultSet.getInt("status_id"));
    dto.setParentId(resultSet.getInt("parent_id"));
    dto.setAuthorId(resultSet.getInt("author_id"));
    dto.setProjectId(resultSet.getInt("project_id"));
    dto.setAssign(resultSet.getString("user"));
    dto.setPriority(resultSet.getString("priority"));
    dto.setStatus(resultSet.getString("status"));
    return dto;
  }
}