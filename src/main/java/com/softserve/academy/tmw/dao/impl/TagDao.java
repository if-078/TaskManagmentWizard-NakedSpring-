package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.TagDaoInterface;
import com.softserve.academy.tmw.dao.mapper.TagMapper;
import com.softserve.academy.tmw.entity.Tag;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
        String sql = "SELECT * FROM " + table + " WHERE user_id = :userId";
        List<Tag> list =
                jdbcTemplate.query(sql, new MapSqlParameterSource("userId", userId), new TagMapper());
        return list;
    }

    @Override
    public List<Tag> getAllByTaskId(int taskId) {
        String sql = "SELECT tag.id, tag.name, tag.user_id FROM tag\n"
            + "JOIN tags_tasks ON tag.id = tags_tasks.tag_id\n"
            + "WHERE tags_tasks.task_id=:taskId;";
        List<Tag> list =
            jdbcTemplate.query(sql, new MapSqlParameterSource("taskId", taskId), new TagMapper());
        return list;
    }

    @Override
    public boolean setTagsToTask(int[] tags, int taskId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        StringBuilder sql = new StringBuilder("INSERT into tags_tasks (tag_id, task_id) VALUES ");
        param.addValue("task", taskId);
        for (int i = 0; i < tags.length; i++) {
            String tag = "tag" + i;
            sql.append("(:" + tag + ", :task),");
            param.addValue(tag, tags[i]);
        }
        sql.setLength(sql.length() - 1);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //System.out.println(jdbcTemplate.update(sql.toString(), param));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(sql);
        //return false;
        return jdbcTemplate.update(sql.toString(), param) > 0;
    }

    /*public boolean addTagsToTask(int[] tagId, int taskId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        StringBuilder sql = new StringBuilder("INSERT into tags_tasks (tag_id, task_id) VALUES ");
        param.addValue("task", taskId);
        for (int i = 0; i < tagId.length; i++) {
            String tag = "tag" + i;
            sql.append("(tag_id:" + tag + ", task_id:task),");
            param.addValue(tag, tagId[i]);
        }
        sql.setLength(sql.length() - 1);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(jdbcTemplate.update(sql.toString(), param));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return false;
    }*/

    private MapSqlParameterSource getParameters(Tag entity) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", entity.getId());
        param.addValue("name", entity.getName());
        param.addValue("user_id", entity.getUserId());
        return param;
    }


    /*@Autowired
    static TagDao tagDao = new TagDao("tmw.tag");

    public static void main(String[] args) {
        int[] tagsList = new int[2];
        *//*for (int i = 0; i < tagsList.length; i++) {
            tagsList[i] = i + 1;
        }
        for (int i = 0; i < tagsList.length; i++) {
            System.out.println(tagsList[i]);
        }*//*
        tagsList[0] = 1;
        tagsList[1] = 2;
        System.out.println(tagDao);
        tagDao.setTagsToTask(tagsList, 3);
    }*/

}