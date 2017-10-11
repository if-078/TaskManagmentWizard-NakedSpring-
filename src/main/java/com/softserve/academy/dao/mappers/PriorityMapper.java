package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Priority;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PriorityMapper implements RowMapper<Priority> {

  @Override
  public Priority mapRow(ResultSet rs, int i) throws SQLException {
    return new Priority(rs.getInt("id"), rs.getString("name"));
  }

}
