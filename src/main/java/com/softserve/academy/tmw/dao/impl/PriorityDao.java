package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.mapper.PriorityMapper;
import com.softserve.academy.tmw.entity.Priority;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:tables.properties")
public class PriorityDao extends EntityDao<Priority> {

  public PriorityDao(@Value("${priority}") String table) {
    super(table, new PriorityMapper());
  }

  @Override
  public Priority create(Priority entity) {
    throw new UnsupportedOperationException("create operation not available for priority table");
  }

  @Override
  public boolean update(Priority entity) {
    throw new UnsupportedOperationException("update operation not available for priority table");
  }


}