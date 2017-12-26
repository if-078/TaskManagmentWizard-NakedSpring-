package com.softserve.academy.tmw.dao.api;


import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.entity.UsersTasks;

import java.util.List;

public interface UsersTasksDaoInterface extends EntityDaoInterface<UsersTasks>{

    public List<User> getTeamByTask(int taskId, int userId);

}
