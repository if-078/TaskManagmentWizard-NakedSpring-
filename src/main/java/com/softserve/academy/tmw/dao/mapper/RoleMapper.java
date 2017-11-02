package com.softserve.academy.tmw.dao.mapper;

import com.softserve.academy.tmw.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role(resultSet.getInt("id"), resultSet.getString("name"));
        return role;
    }

}
