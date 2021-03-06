package com.softserve.academy.tmw.dao.mapper;

import com.softserve.academy.tmw.entity.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class TagMapper implements RowMapper<Tag> {

  @Override
  public Tag mapRow(ResultSet rs, int i) throws SQLException {
    Tag tag = new Tag();
    tag.setId(rs.getInt("id"));
    tag.setName(rs.getString("name"));
    tag.setUserId(rs.getInt("user_id"));
    tag.setProjectId(rs.getInt("project_id"));
    return tag;
  }

}

