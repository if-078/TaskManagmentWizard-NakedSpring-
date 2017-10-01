package com.softserve.if078.tmwSpring.dao;

import com.softserve.if078.tmwSpring.entities.Priority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class PriorityDao implements DaoInterface<Priority>{


        private static final String TABLE = "tmw.priority";
        private static final String TABLE_ID  = "id";
        private static final String TABLE_NAME = "name";

        @Autowired
        @Qualifier("dataSource")
        DataSource dataSource;

        @Override
        public List<Priority> getAll() throws SQLException {
            List<Priority> res = new ArrayList<>();
            String query = "Select " + TABLE_ID + " " + TABLE_NAME + " from " + TABLE;
            try (Statement statement = dataSource.getConnection().createStatement();
                 ResultSet resultSet = statement.executeQuery(query)){
                while (resultSet.next()){
                    Priority priority = new Priority();
                    priority.setId(resultSet.getInt(1));
                    priority.setName(resultSet.getString(2));
                    res.add(priority);
                }
            }
            return res;
        }

        @Override
        public Priority findOne(int id) throws SQLException {
            Priority result = new Priority();
            result.setId(id);
            String query = "Select " +TABLE_NAME + " from " + TABLE + " where " + TABLE_ID + " = ?";
            try (PreparedStatement statement = dataSource.getConnection().prepareStatement(query)){
                statement.setInt(1, id);
                try(ResultSet set = statement.executeQuery()){
                    if (set.next()){
                        result.setName(set.getString(1));
                    }else throw new SQLException("Not found " + id +" ID in "+ TABLE+" table");
                }
            }
            return result;
        }

        @Override
        public boolean update(Priority entity) {
            throw new UnsupportedOperationException("Update operation not supported for priority table");
        }

        @Override
        public boolean delete(int id) {
            throw new UnsupportedOperationException("Delete operation not allowed for priority table");

        }

        @Override
        public Priority create(Priority entity) {
            throw new UnsupportedOperationException("Create operation not allowed for priority table");
        }
}