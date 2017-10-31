package com.softserve.academy.dao.implementation;

import com.softserve.academy.dao.interfaces.TagDaoInterface;
import com.softserve.academy.dao.mappers.TagMapper;
import com.softserve.academy.entity.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource("classpath:tables.properties")
public class TagDao extends EntityDao<Tag> implements TagDaoInterface {

    public TagDao(@Value("${tag}") String table) {
        super(table, new TagMapper());
    }

    @Override
    public Tag create(Tag entity) {
        String sql = "INSERT INTO " + table + " (name, user_id) VALUES (:name, :user_id)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, getParameters(entity), keyHolder);
        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public boolean update(Tag entity) {
        String sql = "UPDATE " + table + " SET name = :name, user_id = :user_id WHERE id = :id";
        return jdbcTemplate.update(sql, getParameters(entity)) == 1;
    }

    @Override
    public boolean deleteAllByUserId(int userId) {
        String Sql = "DELETE FROM " + table + " WHERE user_id = :user_id";
        return jdbcTemplate.update(Sql, new MapSqlParameterSource("user_id", userId)) > 0;
    }

    @Override
    public List<Tag> getAllByUserId(int userId) {
        String sql = "SELECT * FROM " + table + " WHERE user_id = :user_id";
        List<Tag> list =
                jdbcTemplate.query(sql, new MapSqlParameterSource("user_id", userId), new TagMapper());
        return list;
    }

    private MapSqlParameterSource getParameters(Tag entity) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", entity.getId());
        param.addValue("name", entity.getName());
        param.addValue("user_id", entity.getUserId());
        return param;
    }

}