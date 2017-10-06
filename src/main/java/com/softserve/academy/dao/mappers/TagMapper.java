
package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Tag;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements RowMapper<Tag> {

  @Override
  public Tag mapRow(ResultSet rs, int i) throws SQLException {
    ResultSetMetaData rsmd = rs.getMetaData();

    Tag tag = new Tag();
    tag.setId(rs.getInt("id"));
    tag.setName(rs.getString("name"));
    tag.setId(rs.getInt("user_id"));
    return tag;
  }

}

