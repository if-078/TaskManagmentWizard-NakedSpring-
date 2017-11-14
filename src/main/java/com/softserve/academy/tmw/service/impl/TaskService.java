package com.softserve.academy.tmw.service.impl;
import com.softserve.academy.tmw.dto.TaskDTO;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import com.softserve.academy.tmw.dao.util.wrapper.FilterStateWrapper;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dao.util.JooqSQLBuilder;
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

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public Task createTaskByDTO(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setAssignTo(taskDTO.getAssignTo());
        task.setCreatedDate(new Date());
        task.setStartDate(getFormatDate(taskDTO.getStartDate()));
        task.setEndDate(getFormatDate(taskDTO.getEndDate()));
        task.setEstimateTime(getTimeFormat(taskDTO.getEstimateTime()));
        task.setPriorityId(taskDTO.getPriorityId());
        task.setParentId(taskDTO.getParentId());
        task.setStatusId(taskDTO.getStatusId());
        task.setTags(taskDTO.getTags());

        return taskDao.create(task);
    }

    @Override
    public boolean updateTaskByDTO(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setAssignTo(taskDTO.getAssignTo());
        task.setCreatedDate(new Date());
        task.setStartDate(getFormatDate(taskDTO.getStartDate()));
        task.setEndDate(getFormatDate(taskDTO.getEndDate()));
        task.setEstimateTime(getTimeFormat(taskDTO.getEstimateTime()));
        task.setPriorityId(taskDTO.getPriorityId());
        task.setParentId(taskDTO.getParentId());
        task.setStatusId(taskDTO.getStatusId());
        task.setTags(taskDTO.getTags());

        return taskDao.update(task);
    }

    private Date getFormatDate(String line){
        try {
            return new SimpleDateFormat( "yyyy-MM-dd" ).parse(line);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private Time getTimeFormat(String line){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = sdf.parse(line);
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);

            return new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Time(00,00,00);
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
    public List<TaskTableDTO> getFilteredTasksForTable(int parentId, String[] dates, int[] status, int[] priority, int[] tag){
        FilterStateWrapper wrapper = new FilterStateWrapper();
        wrapper.setId(parentId);
        wrapper.setPriority(priority);
        wrapper.setStatus(status);
        wrapper.setTag(tag);
        wrapper.setDates(dates);
        JooqSQLBuilder builder = new JooqSQLBuilder(wrapper);

        List<Task> tasksList = taskDao.getFilteredTasks(builder);
        List<TaskTableDTO> tasksDTOList = new ArrayList<>();

        List<User> users = userDao.getAll();
        List<Priority>priorities = servicePriority.getAll();
        List<Status>statuses =serviceStatus.getAll();

        for (Task task : tasksList){
            TaskTableDTO dto = new TaskTableDTO();
            dto.setId(task.getId());
            dto.setName(task.getName());
            dto.setStartDate(task.getStartDate());
            dto.setEstimateTime(task.getEstimateTime());
            for (User user : users){
                if (user.getId() == task.getAssignTo()) {
                    dto.setAssignTo(user.getName());
                    break;
                }
            }
            for (Priority priorit : priorities){
                if (priorit.getId() == task.getPriorityId()){
                    dto.setPriority(priorit.getName());
                    break;
                }
            }
            for (Status stat : statuses){
                if (task.getStatusId() == stat.getId()){
                    dto.setStatus(stat.getName());
                    break;
                }
            }
            tasksDTOList.add(dto);
        }
        return tasksDTOList;
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