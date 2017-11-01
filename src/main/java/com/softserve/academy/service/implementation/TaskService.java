package com.softserve.academy.service.implementation;

import com.softserve.academy.DTO.FilterStateWrapper;
import com.softserve.academy.DTO.TaskTableDto;
import com.softserve.academy.dao.implementation.JooqSQLBuilder;
import com.softserve.academy.dao.implementation.PriorityDao;
import com.softserve.academy.dao.implementation.StatusDao;
import com.softserve.academy.dao.implementation.UserDao;
import com.softserve.academy.dao.interfaces.TaskDaoInterface;
import com.softserve.academy.dao.interfaces.UserDaoInterface;
import com.softserve.academy.entity.*;
import com.softserve.academy.service.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInterface {

    @Autowired
    TaskDaoInterface taskDao;
    @Autowired
    UserDaoInterface userDaoInterface;
    @Autowired
    PriorityDao priorityDao;
    @Autowired
    StatusDao statusDao;

    @Override
    public List<Task> getAll() {
        return taskDao.getAll();
    }

    @Override
    public Task findOne(int id) {
        return taskDao.findOne(id);
    }

    @Override
    public boolean update(Task task) {
        return taskDao.update(task);
    }

    @Override
    public boolean delete(int id) {
        return taskDao.delete(id);
    }

    @Override
    public Task create(Task task) {
        return taskDao.create(task);
    }

    @Override
    public List<Task> getTasksForToday() {
        return taskDao.getTasksForToday();
    }

    @Override
    public List<Tag> getTagsOfTask(int taskId) {
        return taskDao.getTagsOfTask(taskId);
    }

    @Override
    public List<Comment> getCommentsOfTask(int taskId) {
        return taskDao.getCommentsOfTask(taskId);
    }

    @Override
    public List<Task> getSubtasks(int id) {
        return taskDao.getSubTasks(id);
    }

    public List<Task> getUserTask(int userId) {  //todo: make impl of this method in dao with sql query
        return getAll().stream().filter(s -> s.getAssign_to() == userId).collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByTag(int tagId) {
        return taskDao.getTasksByTag(tagId);
    }

    @Override
    public List<TaskTableDto> getFilteredTasksForTable(int parentId, String[] dates, int[] status, int[] priority, int[] tag){
        FilterStateWrapper wrapper = new FilterStateWrapper();
        wrapper.setId(parentId);
        wrapper.setPriority(priority);
        wrapper.setStatus(status);
        wrapper.setTag(tag);
        JooqSQLBuilder builder = new JooqSQLBuilder(wrapper);

        List<Task> list = taskDao.getFilteredTasks(builder);
        //List<Task> list = taskDao.getAll();
        List<TaskTableDto> result = new ArrayList<>();
        List<User> users = userDaoInterface.getAll();
        List<Priority>priorities = priorityDao.getAll();
        List<Status>statuses = statusDao.getAll();


        for (Task t : list){
            TaskTableDto dto = new TaskTableDto();
            dto.setId(t.getId());
            dto.setName(t.getName());
            dto.setStartDate(t.getStart_date());
            dto.setEstimateTime(t.getEstimate_time());
            for (User user : users){
                if (user.getId() == t.getAssign_to()) {
                    dto.setAssignTo(user.getName());
                    break;
                }
            }
            for (Priority p : priorities){
                if (p.getId() == t.getPriority_id()){
                    dto.setPriority(p.getName());
                    break;
                }
            }
            for (Status s : statuses){
                if (t.getStatus_id() == s.getId()){
                    dto.setStatus(s.getName());
                    break;
                }
            }
            result.add(dto);
        }

        return result;
    }

  /*
   * /@Override public ArrayList<Task> getTaskByStatus(int statusId) { return
   * taskDao.getTaskByStatus(statusId); }
   * 
   * //@Override public ArrayList<Task> getTaskByPriority(int priorityId) { return
   * taskDao.getTaskByPriority(priorityId); }
   * 
   * //@Override public ArrayList<Task> getTasksCreatedByUser(int userId) { return
   * taskDao.getTasksCreatedByUser(userId); }
   * 
   * //@Override public ArrayList<Task> getTasksAssignToUser(int userId) { return
   * taskDao.getTasksAssignToUser(userId); }
   * 
   * //@Override public ArrayList<Task> getTasksByTag(int tagId) { return
   * taskDao.getTasksByTag(tagId); }
   * 
   * //@Override public ArrayList<Task> treeOfTasks(int taskId) { return
   * taskDao.treeOfTasks(taskId); }
   * 
   * //@Override public User getAuthorOfTask(int taskId) { return taskDao.getAuthorOfTask(taskId); }
   */

}