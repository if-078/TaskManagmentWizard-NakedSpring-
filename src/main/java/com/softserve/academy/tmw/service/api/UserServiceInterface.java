package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.entity.User;

public interface UserServiceInterface extends EntityServiceInterface<User> {
    User findByEmail(String email);
}
