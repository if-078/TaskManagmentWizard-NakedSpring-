package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.SpentTimeDaoInterface;
import com.softserve.academy.tmw.dao.mapper.SpentTimeMapper;
import com.softserve.academy.tmw.entity.SpentTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@PropertySource("classpath:tables.properties")
public class SpentTimeDao extends EntityDao<SpentTime> implements SpentTimeDaoInterface {

    public SpentTimeDao(@Value("${spent_time}") String table) {
        super(table, new SpentTimeMapper());
    }

    @Override
    public SpentTime create(SpentTime entity) {

        String sql = "INSERT INTO " + table +
                " (user_id, task_id, date, log_time) VALUES (:user_id, :task_id, :date_t, :log_time)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        param.addValue("user_id", entity.getUserId());
        param.addValue("task_id", entity.getTaskId());
        param.addValue("date_t", new Date());
        param.addValue("log_time", entity.getLogTime());
        jdbcTemplate.update(sql, param, keyHolder);
        entity.setId(keyHolder.getKey().intValue());

        return null;
    }

    @Override
    public boolean update(SpentTime entity) {
        return false;
    }

    @Override
    public int getTotalSpentTimeByTask(int taskId) {

        String query = "select sum(log_time) from tmw.spent_time where task_id = taskId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("taskId", taskId);

        return jdbcTemplate.update(query, parameterSource);
    }

}