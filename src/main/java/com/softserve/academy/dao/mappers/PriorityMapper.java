/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softserve.academy.dao.mappers;

import com.softserve.academy.entity.Priority;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PriorityMapper implements RowMapper<Priority>{

    @Override
    public Priority mapRow(ResultSet rs, int i) throws SQLException {
       return new Priority(rs.getInt("id"), rs.getString("name"));
    }
    
}
