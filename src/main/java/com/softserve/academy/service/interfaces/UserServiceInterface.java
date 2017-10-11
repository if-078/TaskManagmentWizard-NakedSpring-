package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.User;

public interface UserServiceInterface extends EntityServiceInterface<User> {
  User findByEmail(String email);
}
