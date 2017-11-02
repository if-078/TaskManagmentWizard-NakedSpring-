package com.softserve.academy.tmw.service.impl;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import com.softserve.academy.tmw.wrapper.FilterStateWrapper;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dao.impl.JooqSQLBuilder;
import com.softserve.academy.tmw.dao.api.TaskDaoInterface;
import com.softserve.academy.tmw.dao.api.UserDaoInterface;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.entity.*;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import com.softserve.academy.tmw.service.api.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService implements TaskServiceInterface {

    @Autowired
    TaskDaoInterface taskDao;

    @Autowired
    UserDaoInterface userDao;

    @Autowired
    EntityServiceInterface<Status> serviceStatus;

    @Autowired
    EntityServiceInterface<Priority> servicePriority;

    @Autowired
    UserServiceInterface serviceUser;



    @Override
    public List<Task> getAll() {
        return taskDao.getAll();
    }

    @Override
    public Task findOne(int id) {
        try {
            return taskDao.findOne(id);
        } catch (EmptyResultDataAccessException e) {
            return new Task();
        }
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
        try {
            return taskDao.create(task);
        } catch (DataAccessException e) {
            return new Task();
        }
    }


    @Override
    public List<Task> getTasksForToday() {
        return taskDao.getTasksForToday();
    }

    @Override
    public List<Task> getSprint() {
        return taskDao.getSprint();
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
        return taskDao.getSubtasks(id);
    }

    @Override
    public List<Task> getTasksAssignToUser(int userId) {
        return taskDao.getTasksAssignToUser(userId);
    }

    @Override
    public List<TaskTableDTO> getFilteredTasksForTable(int parentId, long[] dates, int[] status, int[] priority, int[] tag){

        // tag filter not implemented
        tag = new int[0];

        // time filter not implemented
        Date dateFrom = new Date(dates[0]);
        Date dateTo = new Date(dates[1]);


        FilterStateWrapper wrapper = new FilterStateWrapper();
        wrapper.setId(parentId);
        wrapper.setPriority(priority);
        wrapper.setStatus(status);
        wrapper.setTag(tag);
        JooqSQLBuilder builder = new JooqSQLBuilder(wrapper);

        List<Task> list = taskDao.getFilteredTasks(builder);
        List<TaskTableDTO> result = new ArrayList<>();
        List<User> users = userDao.getAll();
        List<Priority>priorities = servicePriority.getAll();
        List<Status>statuses =serviceStatus.getAll();

        for (Task t : list){
            TaskTableDTO dto = new TaskTableDTO();
            dto.setId(t.getId());
            dto.setName(t.getName());
            dto.setStartDate(t.getStartDate());
            dto.setEstimateTime(t.getEstimateTime());
            for (User user : users){
                if (user.getId() == t.getAssignTo()) {
                    dto.setAssignTo(user.getName());
                    break;
                }
            }
            for (Priority p : priorities){
                if (p.getId() == t.getPriorityId()){
                    dto.setPriority(p.getName());
                    break;
                }
            }
            for (Status s : statuses){
                if (t.getStatusId() == s.getId()){
                    dto.setStatus(s.getName());
                    break;
                }
            }
            result.add(dto);
        }
        return result;
    }


     @Override
    public List<TaskTreeDTO> findTaskByTree(int id){

        List<Task> allTask = taskDao.getAll();

        List<Task> selectedTask = new ArrayList<Task>();
        for (Task task : allTask){
            if(task.getParentId() == id) selectedTask.add(task);
        }

        List<TaskTreeDTO> allTaskDTO = new ArrayList<>();
        for(Task task : selectedTask){
            TaskTreeDTO taskDTO = new TaskTreeDTO();
            taskDTO.setId(task.getId());
            taskDTO.setText(task.getName());
            allTaskDTO.add(taskDTO);
        }

        for(TaskTreeDTO taskDTO : allTaskDTO){
            for(Task task : allTask){
                if (taskDTO.getId()==task.getParentId()){
                    taskDTO.setChildren(true);
                    break;
                }
            }
        }
        return allTaskDTO;
    }


    @Override
    public TaskFullInfoDTO getFullInfo(int id){
        Task task = taskDao.findOne(id);
        TaskFullInfoDTO taskDTO = new TaskFullInfoDTO();

        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setCreatedDate(task.getCreatedDate());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setEstimateTime(task.getEstimateTime());
        taskDTO.setAssignTo(serviceUser.findOne(task.getAssignTo()));
        taskDTO.setStatus(serviceStatus.findOne(task.getStatusId()));
        taskDTO.setPriority(servicePriority.findOne(task.getStatusId()));

        return taskDTO;
    }

}