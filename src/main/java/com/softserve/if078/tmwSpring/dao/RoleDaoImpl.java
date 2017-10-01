package com.softserve.if078.tmwSpring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.softserve.if078.tmwSpring.entities.Role;

@Component
public class RoleDaoImpl implements RoleDao {

    private final String tabName = "tmw.role";

    @Autowired
    @Qualifier("dataSource")
    DataSource datasource;

    @Override
    public Role create(Role role) throws SQLException {
        if (role.getId() < 0 || role.getName().equals("") || role.getName().equals(null))
            throw new IllegalArgumentException("Incorrect arguments of role");
        try (PreparedStatement preparedStatement = datasource.getConnection().prepareStatement("insert into " +
                tabName + " (name) values (?)", Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                role.setId(resultSet.getInt(1));
            }
        }
        return role;
    }

    @Override
    public List<Role> create(Role... roles) throws SQLException {
        List<Role> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(create(role));
        }
        return list.size() > 0 ? list : null;
    }

    @Override
    public Role findOne(int id) throws SQLException {
        if (id < 0) throw new IllegalArgumentException("Incorrect argument");
        try (Statement statement = datasource.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from " +
                    tabName + " where role_id=" + id)) {
            if (resultSet.next()) {
                return new Role(resultSet.getInt("role_id"), resultSet.getString("name"));
            }
            return null;
        }
    }

    @Override
    public List<Role> getAll() throws SQLException {
        try (Statement statement = datasource.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from " + tabName);
            List<Role> roles = new ArrayList<>();
                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getInt("role_id"), resultSet.getString("name")));
                }
                if (roles.size() > 0) return roles;
            }
            return null;
        }

    @Override
    public boolean update(Role role) throws SQLException {
        if (role.getName().equals("") || role.getName().equals(null))
            throw new IllegalArgumentException("Incorrect arguments of role");
        try (PreparedStatement preparedStatement = datasource.getConnection().prepareStatement("Update " +
                tabName + " Set name =? Where role_id=?")) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setInt(2, role.getId());
            return preparedStatement.executeUpdate() != 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        if (id < 0) throw new IllegalArgumentException();
        try (Statement statement = datasource.getConnection().createStatement()) {
                return statement.executeUpdate("Delete From " +
                        tabName + " Where role_id=" + id) != 0;
        }
    }

    @Override
    public boolean deleteAll() throws SQLException {
        try (Statement statement = datasource.getConnection().createStatement()) {
            return statement.executeUpdate("Delete from " + tabName) != 0;
        }
    }
}

