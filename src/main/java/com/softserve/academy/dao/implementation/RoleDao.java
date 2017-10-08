package com.softserve.academy.dao.implementation;

import com.softserve.academy.entity.Role;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:tables.properties")
public class RoleDao extends Dao<Role> {

    public RoleDao() {
    }


  @Override
  public Role create(Role role) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    param.addValue("name", role.getName());
    jdbcTemplate.update("INSERT INTO " + table + " (name) VALUES (:name)", param, keyHolder);
    role.setId( keyHolder.getKey().intValue());
    return role;
  }

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

  public boolean deleteAll() {
    return jdbcTemplate.update("DELETE FROM " + table , new MapSqlParameterSource()) == 1;
  }

    @Autowired
    public void setTable(@Value("${role}")String table) {
        this.table = table;
    }
    @Autowired
    public void setMapper(RowMapper<Role> mapper) {
        this.mapper = mapper;
    }
   
}

