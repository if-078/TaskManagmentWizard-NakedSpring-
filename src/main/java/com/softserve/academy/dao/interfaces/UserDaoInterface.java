package com.softserve.academy.dao.interfaces;

import com.softserve.academy.entity.User;

public interface UserDaoInterface extends EntityDaoInterface<User> {
    User findByEmail(String email);
}
