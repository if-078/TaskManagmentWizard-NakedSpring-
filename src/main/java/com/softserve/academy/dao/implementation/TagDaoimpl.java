package com.softserve.academy.dao.implementation;

import com.softserve.academy.dao.mappers.TagMapper;
import com.softserve.academy.entity.Tag;
import java.util.List;
import javax.sql.DataSource;
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
public class TagDaoimpl extends Dao<Tag> {

    public TagDaoimpl() {
    }


  public List<Tag> getAllByUserId(int userId) {
    String sql = "SELECT * FROM " + table + " WHERE user_id = :user_id";
    List<Tag> list =
        operations.query(sql, new MapSqlParameterSource("user_id", userId), new TagMapper());
    return list;
  }

  @Override
  public Tag create(Tag entity) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO " + table + " (name, user_id) VALUES (:name, :user_id)";
    param.addValue("name", entity.getName());
    param.addValue("user_id", entity.getUserId());
    operations.update(sql, param, keyHolder);
    entity.setId( keyHolder.getKey().intValue());
    return entity;
  }

  @Override
  public boolean update(Tag entity) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    String sql = "UPDATE " + table + " SET name = :name, user_id = :user_id WHERE id = :id";
    param.addValue("name", entity.getName());
    param.addValue("user_id", entity.getUserId());
    param.addValue("id", entity.getId());
    return operations.update(sql, param) == 1;

  }

  public boolean deleleAllByUserId(int userId) {
    String Sql = "DELETE FROM " + table + " WHERE user_id = :user_id";
    return operations.update(Sql, new MapSqlParameterSource("user_id", userId)) > 0;
  }
    @Autowired
    public void setTable(@Value("${tag}")String table) {
        this.table = table;
    }
    @Autowired
    public void setMapper(RowMapper<Tag> mapper) {
        this.mapper = mapper;
    }
  
  

}

