package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.User;

public interface UserService extends ServiceInterface<User> {
	User findByEmail(String email);
}
