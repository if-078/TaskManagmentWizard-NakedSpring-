package it.com.softserve.academy.tmw.dao.utility;

import com.softserve.academy.tmw.dao.impl.RoleDao;
import com.softserve.academy.tmw.entity.Role;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


public class RolePopulator {

  private NamedParameterJdbcTemplate jdbcTemplate;
  private RoleDao dao;

  @Autowired
  public void setDao(RoleDao dao) {
    this.dao = dao;
  }

  @Autowired
  public void setJdbcTemplate(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  public void initTable() {
    initOneEntity("USER");
    initOneEntity("OWNER");
  }

  public Role initOneEntity(String name) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    Role role = new Role(name);
    param.addValue("name", name);
    jdbcTemplate.update("INSERT INTO tmw.role (name) VALUES (:name)", param, keyHolder);
    role.setId(keyHolder.getKey().intValue());
    return role;

  }

  public void deleteRecordsOfTable() {
    jdbcTemplate.update("DELETE FROM tmw.role", new MapSqlParameterSource());
  }

  public Role createDefaultEntity() {
    initTable();
    return dao.findOne(1);
  }
}
