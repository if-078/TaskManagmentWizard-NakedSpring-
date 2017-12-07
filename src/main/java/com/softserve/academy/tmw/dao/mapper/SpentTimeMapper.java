package com.softserve.academy.tmw.dao.mapper;

import com.softserve.academy.tmw.entity.SpentTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpentTimeMapper implements RowMapper<SpentTime> {

    @Override
    public SpentTime mapRow(ResultSet rs, int i) throws SQLException {
        SpentTime spentTime = new SpentTime(rs.getInt(1), rs.getInt(2),
                                            rs.getInt(3), rs.getDate(4), rs.getInt(5));

        return spentTime;
    }
}
