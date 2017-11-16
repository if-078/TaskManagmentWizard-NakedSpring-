package com.softserve.academy.tmw.dao.mapper;


import com.softserve.academy.tmw.dto.TaskTableDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapperForTable implements RowMapper<TaskTableDTO> {

    @Override
    public TaskTableDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        TaskTableDTO dto = new TaskTableDTO();
        dto.setId(resultSet.getInt("id"));
        dto.setName(resultSet.getString("name"));
        dto.setEstimateTime(resultSet.getTime("estimate_time"));
        dto.setPriority(resultSet.getString("priority"));
        dto.setStatus(resultSet.getString("status"));
        dto.setStartDate(resultSet.getDate("start_date"));
        dto.setAssignTo(resultSet.getString("user"));
        return dto;
    }
}
/*        String query = "SELECT task.id, task.name, task.created_date, task.planning_date, task.start_date,
                    task.end_date, task.estimate_time,\n"
           + "  user.name as user, status.name as status, priority.name as priority, task.parent_id\n"
           + "  FROM task\n"
           + "  LEFT JOIN priority ON task.priority_id = priority.id\n"
           + "  LEFT JOIN status ON task.status_id = status.id\n"
           + "  LEFT JOIN user ON task.assign_to = user.id"
           + "WHERE task.id=:id"; */