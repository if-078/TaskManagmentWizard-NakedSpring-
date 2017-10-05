package utility;

import com.softserve.academy.dao.implementation.TaskDao;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class TaskPopulator {

    private TaskDao dao;
    private UserPopulator userPopulator;
    private StatusPopulator statusPopulator;
    private PriorityPopulator priorityPopulator;

    @Autowired
    public void setDao(TaskDao dao) {
        this.dao = dao;
    }

    @Autowired
    public void setUserPopulator(UserPopulator userPopulator) {
        this.userPopulator = userPopulator;
    }

    @Autowired
    public void setStatusPopulator(StatusPopulator statusPopulator) {
        this.statusPopulator = statusPopulator;
    }

    @Autowired
    public void setPriorityPopulator(PriorityPopulator priorityPopulator) {
        this.priorityPopulator = priorityPopulator;
    }


    public Task createDefaultHeadTask() throws SQLException {
        int hourFromMillSecs = 1000 * 60 * 60;
        Task task = new Task();
        User defUser = userPopulator.createDefaultUser();
        task.setName("Default task name");
        task.setAssign_to(defUser.getId());
        task.setCreated_date(new Date(System.currentTimeMillis()));
        task.setEnd_date(new Date(System.currentTimeMillis() + (7 * hourFromMillSecs * 24))); //end date after one week
        task.setStart_date(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
        task.setEstimate_time(new Time(2, 0, 0));
        task.setId(-1);
        task.setParent_id(0);
        task.setPriority_id(priorityPopulator.initOnePriority("HIGH").getId());
        task.setStatus_id(statusPopulator.createDefaultStatus().getId());
        return dao.create(task);
    }


    public Task createDefaultHeadTaskWithCustomUser(User user) throws SQLException {
        int hourFromMillSecs = 1000 * 60 * 60;
        Task task = new Task();
        task.setName("Default task name");
        task.setAssign_to(user.getId());
        task.setCreated_date(new Date(System.currentTimeMillis()));
        task.setEnd_date(new Date(System.currentTimeMillis() + (7 * hourFromMillSecs * 24))); //end date after one week
        task.setStart_date(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
        task.setEstimate_time(new Time(2, 0, 0));
        task.setId(-1);
        task.setParent_id(0);
        task.setPriority_id(priorityPopulator.initOnePriority("HIGH").getId());
        task.setStatus_id(statusPopulator.createDefaultStatus().getId());
        return dao.create(task);
    }
}