package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.RoleDaoInterface;
import com.softserve.academy.tmw.dao.mapper.RoleMapper;
import com.softserve.academy.tmw.entity.Role;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class RoleDao extends EntityDao<Role> implements RoleDaoInterface {

  public RoleDao(@Value("${role}") String table) {
    super(table, new RoleMapper());
  }

  @Override
  public Role create(Role role) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    param.addValue("name", role.getName());
    jdbcTemplate.update("INSERT INTO " + table + " (name) VALUES (:name)", param, keyHolder);
    role.setId(keyHolder.getKey().intValue());
    return role;
  }

  @Override
  public List<Role> addBatch(Role... roles) {
    List<Role> list = new ArrayList<>();
    for (Role role : roles) {
      list.add(create(role));
    }
    return list;
  }

  @Override
  public boolean update(Role role) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("name", role.getName());
    param.addValue("id", role.getId());
    return jdbcTemplate.update("UPDATE " + table + " SET name = :name WHERE id = :id", param) == 1;
  }

  @Override
  public boolean deleteAll() {
    return jdbcTemplate.update("DELETE FROM " + table, new MapSqlParameterSource()) == 1;
  }
}