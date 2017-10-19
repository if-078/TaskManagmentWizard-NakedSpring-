package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Status;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusMapper implements RowMapper<Status> {

    @Override
    public Status mapRow(ResultSet rs, int i) throws SQLException {
        Status st = new Status();
        st.setId(rs.getInt("id"));
        st.setName(rs.getString("name"));
        return st;
    }
}