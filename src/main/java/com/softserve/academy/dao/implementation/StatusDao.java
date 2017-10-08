
package com.softserve.academy.dao.implementation;

import com.softserve.academy.entity.Status;
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
public class StatusDao extends Dao<Status> {

    @Autowired    
    public void setTable(@Value("${status}") String table) {
        this.table = table;
    }
    
    @Autowired
    public void setMapper(RowMapper<Status> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Status create(Status entity) {
            MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    param.addValue("name", entity.getName());
    jdbcTemplate.update("INSERT INTO " + table + " (name) VALUES (:name)", param, keyHolder);
    entity.setId( keyHolder.getKey().intValue());
    return entity;
    }

    @Override
    public boolean update(Status entity) {
        MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("name", entity.getName());
    param.addValue("id", entity.getId());
    return jdbcTemplate.update("UPDATE " + table + " SET name = :name WHERE id = :id", param) == 1;
  }
    
    
}
