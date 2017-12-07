package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.entity.SpentTime;

public interface SpentTimeDaoInterface extends EntityDaoInterface<SpentTime>{

    public int getTotalSpentTimeByTask(int taskId);

}
