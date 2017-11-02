package com.softserve.academy.tmw.utility;

import com.softserve.academy.tmw.dao.impl.StatusDao;
import com.softserve.academy.tmw.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusPopulator implements Populator<Status> {
  private StatusDao dao;

    @Autowired
    public void setDao(StatusDao dao) {
        this.dao = dao;
    }

    @Override
    public Status createDefaultEntity() {
        Status status = new Status();
        status.setId(-1);
        status.setName("In queue");
        return dao.create(status);
    }
}