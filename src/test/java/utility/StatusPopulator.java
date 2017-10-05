package utility;


import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusPopulator{
    private StatusDao dao;

    @Autowired
    public void setDao(StatusDao dao) {
        this.dao = dao;
    }

    public Status createDefaultStatus(){
        Status status = new Status();
        status.setId(-1);
        status.setName("In queue");
        return dao.create(status);
    }
}