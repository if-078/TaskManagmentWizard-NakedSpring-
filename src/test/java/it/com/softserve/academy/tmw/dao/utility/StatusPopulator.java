package it.com.softserve.academy.tmw.dao.utility;

import com.softserve.academy.tmw.dao.impl.StatusDao;
import com.softserve.academy.tmw.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusPopulator  {
  private StatusDao dao;

    @Autowired
    public void setDao(StatusDao dao) {
        this.dao = dao;
    }

    public Status createDefaultEntity() {
        Status status = new Status();
        status.setId(-1);
        status.setName("In queue");
        return dao.create(status);
    }
}