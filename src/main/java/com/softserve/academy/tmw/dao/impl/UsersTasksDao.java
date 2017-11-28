package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.TaskDaoInterface;
import com.softserve.academy.tmw.dao.api.UsersTasksDaoInterface;
import com.softserve.academy.tmw.dao.mapper.UserMapper;
import com.softserve.academy.tmw.dao.mapper.UsersTasksMapper;
import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.entity.UsersTasks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:tables.properties")
public class UsersTasksDao extends EntityDao<UsersTasks> implements UsersTasksDaoInterface {

    public UsersTasksDao(@Value("${users_tasks}") String table) {
        super(table, new UsersTasksMapper());
    }

    @Override
    public UsersTasks create(UsersTasks entity) {
        return null;
    }

    @Override
    public boolean update(UsersTasks entity) {
        return false;
    }

    @Override
    public List<User> getTeamByTask(int taskId, int userId) {
        String query = "select u.* from tmw.user u, (select * from tmw.task where id = " +
                "(select project_id from task where id = :taskId)) t where u.id in (t.assign_to, t.author_id)" +
                " or u.id in (select user_id from tmw.users_tasks where task_id = t.id)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("taskId", taskId);
        List<User> users = jdbcTemplate.query(query, parameterSource, new UserMapper());
        for (User user : users) {
            if (user.getId()==userId) return users;
        }
        return null;
    }

}
