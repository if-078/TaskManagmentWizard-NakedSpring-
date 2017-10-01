package com.softserve.if078.tmwSpring.utility;


import com.softserve.if078.tmwSpring.dao.UserDaoImpl;
import com.softserve.if078.tmwSpring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UserPopulator {

    @Autowired
    UserDaoImpl userDao;

    public User createDefaultUser() throws SQLException {
        User user = new User();
        user.setName("default name");
        user.setPass("******");
        user.setEmail("default@email.com");
        user.setId(-1);
        return userDao.create(user);
    }

    public User createCustomUser(String name, String email) throws SQLException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPass("******");
        return userDao.create(user);
    }
}