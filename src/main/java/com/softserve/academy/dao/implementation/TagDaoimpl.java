/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.dao.implementation;

import com.softserve.academy.dao.mappers.TagMapper;
import com.softserve.academy.entity.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oleg
 */
@Repository
public class TagDaoimpl extends AbstractDao<Tag> {

  @Autowired
  public TagDaoimpl(@Value("tmw.tag") String tagTable,
      @Value("#new TagMapper()") RowMapper<Tag> tagMapper) {
    super(tagTable, tagMapper);
  }

  public List<Tag> getAllByUserId(int user_id) {
    String sql = "SELECT * FROM " + table + " WHERE user_id = :user_id";
    List<Tag> list =
        operations.query(sql, new MapSqlParameterSource("user_id", user_id), new TagMapper());
    return list;
  }

  @Override
  public Tag create(Tag entity) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO " + table + " (name, user_id) VALUES (:name, :user_id)";
    param.addValue("name", entity.getName());
    param.addValue("user_id", entity.getUserId());
    operations.update(sql, param, keyHolder);
    entity.setId((int) keyHolder.getKey());
    return entity;
  }

  @Override
  public boolean update(Tag entity) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    String sql = "UPDATE " + table + " SET name = :name, user_id = :user_id WHERE id = :id";
    param.addValue("name", entity.getName());
    param.addValue("user_id", entity.getUserId());
    param.addValue("id", entity.getId());
    return operations.update(sql, param) == 1;

  }

}
