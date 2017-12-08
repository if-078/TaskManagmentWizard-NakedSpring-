package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.mapper.TaskMapper;
import com.softserve.academy.tmw.entity.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public class JiraIntegrationDao extends EntityDao<Task> {

    public JiraIntegrationDao(@Value("${task}") String table) {
        super(table, new TaskMapper());
    }

    public Task create(Task task) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        java.sql.Timestamp startDate, endDate;

        //if task creating then left_ime = estimate_time
        task.setLeftTime(task.getEstimateTime());

        if (task.getStartDate() == null) {
            startDate = null;
        } else {
            startDate = new java.sql.Timestamp(task.getStartDate().getTime());
        }

        if (task.getEndDate() == null) {
            endDate = null;
        } else {
            endDate = new java.sql.Timestamp(task.getEndDate().getTime());
        }

        if (task.getStatusId() == 0) {
            task.setStatusId(1);
        }
        if (task.getPriorityId() == 0) {
            task.setPriorityId(1);
        }
        String sql = "INSERT INTO " + table
                + " (name, created_date, planning_date, start_date, end_date, estimate_time, spent_time, left_time, assign_to, status_id, "
                + "priority_id, parent_id, author_id, project_id, jira_key) VALUES (:name, :created_date, :planning_date, :start_date, :end_date, :estimate_time, "
                + ":spent_time, :left_time, :assign_to, :status_id, :priority_id, :parent_id, :author_id, :project_id, :jira_key)";

        param.addValue("name", task.getName());
        param.addValue("created_date", task.getCreatedDate());
        param.addValue("planning_date", task.getPlanningDate());
        param.addValue("start_date", startDate);
        param.addValue("end_date", endDate);
        param.addValue("estimate_time", task.getEstimateTime());
        param.addValue("spent_time", task.getSpentTime());
        param.addValue("left_time", task.getLeftTime());
        param.addValue("assign_to", task.getAssignTo());
        param.addValue("status_id", task.getStatusId());
        param.addValue("priority_id", task.getPriorityId());
        param.addValue("parent_id", task.getParentId());
        param.addValue("author_id", task.getAuthorId());
        param.addValue("project_id", task.getProjectId());
        param.addValue("jira_key", task.getJiraKey());
        param.addValue("id", task.getId());

        jdbcTemplate.update(sql, param, keyHolder);
        task.setId(keyHolder.getKey().intValue());

        return task;
    }

    @Override
    public boolean update(Task entity) {
        return false;
    }
}
