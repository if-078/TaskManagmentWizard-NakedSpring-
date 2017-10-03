/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.dao.implementation;

import com.softserve.academy.dao.DaoInterface;
import java.lang.annotation.Target;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @param <E>
 */
@DependsOn("dataSource")
public abstract class AbstractDao<E> implements DaoInterface<E> {
  protected final String table;
  private final RowMapper<E> mapper;

  protected NamedParameterJdbcTemplate operations;

  public AbstractDao(String tablename, RowMapper<E> mapper, DataSource dataSource) {
    this.table = tablename;
    this.mapper = mapper;
    operations = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public List<E> getAll() {
    String sql = "SELECT * FROM " + table;
    List<E> list = operations.query(sql, mapper);
    return list;
  }

  @Override
  public E findOne(int id) {

    String sql = "SELECT * FROM " + table + " WHERE  id = :id";
    return operations.queryForObject(sql, new MapSqlParameterSource("id", id), mapper);
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM " + table + " WHERE id = :id";
    return operations.update(sql, new MapSqlParameterSource("id", id)) == 1;
  }

  @Override
  public abstract E create(E entity);

  @Override
  public abstract boolean update(E entity);

}
