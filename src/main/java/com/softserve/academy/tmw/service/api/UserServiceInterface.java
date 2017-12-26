package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.entity.User;

import java.util.List;

public interface UserServiceInterface extends EntityServiceInterface<User> {

    boolean verify(String key);

    User findByEmail(String email);

    boolean acceptProjectInvitation(String key);

    List<User> getTeamByTask(int taskId, int userId);

    boolean sendEmailToUser(User user, String message, String subject);
}
