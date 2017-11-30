package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.entity.User;

import java.util.List;

public interface UserServiceInterface extends EntityServiceInterface<User> {

    boolean verify(long key);

    User findByEmail(String email);

  List<User> getTeamByTask(int taskId, int userId);
}
