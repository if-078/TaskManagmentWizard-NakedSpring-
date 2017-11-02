package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.entity.User;

public interface UserDaoInterface extends EntityDaoInterface<User> {
    User findByEmail(String email);
}
