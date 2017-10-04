/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements RowMapper<Tag> {

  @Override
  public Tag mapRow(ResultSet rs, int i) throws SQLException {
    Tag tag = new Tag(rs.getInt("id"), rs.getString("name"), rs.getInt("user_id"));
    return tag;
  }

}
