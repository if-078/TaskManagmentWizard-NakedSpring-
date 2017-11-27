package com.softserve.academy.tmw.dao.impl;

import com.softserve.academy.tmw.dao.api.TaskDaoInterface;
import com.softserve.academy.tmw.dao.api.UsersTasksDaoInterface;
import com.softserve.academy.tmw.dao.mapper.UsersTasksMapper;
import com.softserve.academy.tmw.entity.UsersTasks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

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
}
