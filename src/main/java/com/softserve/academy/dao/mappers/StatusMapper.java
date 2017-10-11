
package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class StatusMapper implements RowMapper<Status> {

  @Override
  public Status mapRow(ResultSet rs, int i) throws SQLException {
    Status st = new Status();
    st.setId(rs.getInt("id"));
    st.setName(rs.getString("name"));
    return st;
  }
}
