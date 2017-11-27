package com.softserve.academy.tmw.dao.mapper;


import com.softserve.academy.tmw.entity.UsersTasks;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTasksMapper implements RowMapper<UsersTasks> {

    @Override
    public UsersTasks mapRow(ResultSet rs, int i) throws SQLException {
        UsersTasks userTask = new UsersTasks(rs.getInt(1), rs.getInt(2), rs.getInt(3));

        return userTask;
    }
}
