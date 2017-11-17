package com.softserve.academy.tmw.dto.mapper;

import com.softserve.academy.tmw.dao.api.UserDaoInterface;
import com.softserve.academy.tmw.dao.impl.PriorityDao;
import com.softserve.academy.tmw.dao.impl.StatusDao;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.entity.Status;
import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskTransformator {


  @Autowired
  private UserDaoInterface userDao;
  @Autowired
  private PriorityDao priorityDao;
  @Autowired
  private StatusDao statusDao;

  public List<TaskTableDTO> transformTasksForTree(List<Task> tasks) {
    List<TaskTableDTO> tasksDTOList = new ArrayList<>();

    List<User> users = userDao.getAll();
    List<Priority> priorities = priorityDao.getAll();
    List<Status> statuses = statusDao.getAll();

    tasks.forEach(task -> {
      TaskTableDTO dto = new TaskTableDTO();
      dto.setId(task.getId());
      dto.setName(task.getName());
      dto.setStartDate(task.getStartDate());
      dto.setEstimateTime(task.getEstimateTime());
      users.forEach(user -> {
        if (user.getId() == task.getAssignTo()) {
          dto.setAssignTo(user.getName());
        }
      });
      priorities.forEach(priority1 -> {
        if (priority1.getId() == task.getPriorityId()) {
          dto.setPriority(priority1.getName());
        }
      });
      statuses.forEach(status1 -> {
        if (status1.getId() == task.getStatusId()) {
          dto.setStatus(status1.getName());
        }
      });
      tasksDTOList.add(dto);
    });
    return tasksDTOList;
  }

}