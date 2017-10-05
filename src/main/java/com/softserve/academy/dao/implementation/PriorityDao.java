
package com.softserve.academy.dao.implementation;

import com.softserve.academy.entity.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class PriorityDao extends Dao<Priority>{

    public PriorityDao() {
    }
    
    @Override
    public Priority create(Priority entity) {
        MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    param.addValue("name", entity.getName());
    operations.update("INSERT INTO " + table + " (name) VALUES (:name)", param, keyHolder);
    entity.setId((int) keyHolder.getKey());
    return entity;
  }

    @Override
    public boolean update(Priority entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Autowired
    public void setTable(@Value("${priority}")String table) {
        this.table = table;
    }

    @Autowired
    public void setMapper(RowMapper<Priority> mapper) {
        this.mapper = mapper;
    }
}