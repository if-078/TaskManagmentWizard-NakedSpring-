package com.softserve.academy.dao.implementation;

import com.softserve.academy.dao.mappers.PriorityMapper;
import com.softserve.academy.entity.Priority;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Priority findByName(Priority target) {
        List<Priority> priorities = getAll();
        for (Priority p : priorities) {
            if (p.getName().contains(target.getName())) {
                return p;
            }
        }
        return new Priority();
    }

}