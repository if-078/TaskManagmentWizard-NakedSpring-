package com.softserve.academy.dao.implementation;

import com.softserve.academy.dao.mappers.StatusMapper;
import com.softserve.academy.entity.Status;
import com.softserve.academy.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository
@PropertySource("classpath:tables.properties")
public class StatusDao extends EntityDao<Status> {

  public StatusDao(@Value("${status}") String table) {
    super(table, new StatusMapper());
  }

  @Override
  public Status create(Status entity) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update("INSERT INTO " + table + " (name) VALUES (:name)", getParameters(entity),
        keyHolder);
    entity.setId(keyHolder.getKey().intValue());
    return entity;
  }

  @Override
  public boolean update(Status entity) {

    return jdbcTemplate.update("UPDATE " + table + " SET name = :name WHERE id = :id",
        getParameters(entity)) == 1;
  }

  private MapSqlParameterSource getParameters(Status entity) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("name", entity.getName());
    param.addValue("id", entity.getId());
    return param;
  }


}
