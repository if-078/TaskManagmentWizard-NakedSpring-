package utility;

import com.softserve.academy.dao.implementation.TaskDao;
import com.softserve.academy.entity.Task;
import com.softserve.academy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.Date;

public class TaskPopulator implements Populator<Task> {

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

    @Override
    public Task createDefaultEntity() {
        int hourFromMillSecs = 1000 * 60 * 60;
        Task task = new Task();
        User defUser = userPopulator.createDefaultEntity();
        task.setName("Default task name");
        task.setAssignTo(defUser.getId());
        task.setCreatedDate(new Date(System.currentTimeMillis()));
        task.setEndDate(new Date(System.currentTimeMillis() + (7 * hourFromMillSecs * 24))); //end date after one week
        task.setStartDate(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
        task.setEstimateTime(new Time(2, 0, 0));
        task.setId(-1);
        task.setParentId(0);
        task.setPriorityId(priorityPopulator.initOnePriority("HIGH").getId());
        task.setStatusId(statusPopulator.createDefaultEntity().getId());
        return dao.create(task);
    }


    public Task createDefaultHeadTaskWithCustomUser(User user) {
        int hourFromMillSecs = 1000 * 60 * 60;
        Task task = new Task();
        task.setName("Default task name");
        task.setAssignTo(user.getId());
        task.setCreatedDate(new Date(System.currentTimeMillis()));
        task.setEndDate(new Date(System.currentTimeMillis() + (7 * hourFromMillSecs * 24))); //end date after one week
        task.setStartDate(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
        task.setEstimateTime(new Time(2, 0, 0));
        task.setId(-1);
        task.setParentId(0);
        task.setPriorityId(priorityPopulator.initOnePriority("HIGH").getId());
        task.setStatusId(statusPopulator.createDefaultEntity().getId());
        return dao.create(task);
    }
}