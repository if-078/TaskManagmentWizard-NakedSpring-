
package com.softserve.academy.dao.implementation;


import com.softserve.academy.dao.interfaces.EntityDao;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@DependsOn("dataSource")
public abstract class Dao<E> implements EntityDao<E> {
  protected String table;
  protected RowMapper<E> mapper;
  protected NamedParameterJdbcTemplate jdbcTemplate;

  public Dao(String tablename, RowMapper<E> mapper) {
    this.table = tablename;
    this.mapper = mapper;

  }

  public Dao() {

  }

  @Autowired
  private void setJdbcTemplate(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public List<E> getAll() {
    String sql = "SELECT * FROM " + table;
    List<E> list = jdbcTemplate.query(sql, mapper);
    return list;
  }

  @Override
  public E findOne(int id) {
    String sql = "SELECT * FROM " + table + " WHERE  id = :id";
    return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), mapper);
  }

  @Override
  public boolean delete(int id) {
    String sql = "DELETE FROM " + table + " WHERE id = :id";
    return jdbcTemplate.update(sql, new MapSqlParameterSource("id", id)) == 1;
  }

  @Override
  public abstract E create(E entity);

  @Override
  public abstract boolean update(E entity);

}
