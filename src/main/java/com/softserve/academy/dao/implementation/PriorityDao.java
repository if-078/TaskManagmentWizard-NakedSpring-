
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
        throw new UnsupportedOperationException("create operation not available for priority table");
  }

    @Override
    public boolean update(Priority entity) {
        throw new UnsupportedOperationException("update operation not available for priority table");
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