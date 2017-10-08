package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.User;

public interface UserService extends EntityService<User> {
  User findByEmail(String email);
}
