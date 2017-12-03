package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.api.TaskDaoInterface;
import com.softserve.academy.tmw.dao.api.UserDaoInterface;
import com.softserve.academy.tmw.dao.api.UsersTasksDaoInterface;
import com.softserve.academy.tmw.dao.util.JooqSQLBuilder;
import com.softserve.academy.tmw.dao.util.wrapper.FilterStateWrapper;
import com.softserve.academy.tmw.dto.TaskDTO;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.entity.*;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import com.softserve.academy.tmw.service.api.TagServiceInterface;
import com.softserve.academy.tmw.service.api.TaskServiceInterface;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class TaskService implements TaskServiceInterface {

  public TaskService() {

  }

  private TransactionTemplate transactionTemplate;

  @Autowired
  private TaskDaoInterface taskDao;


  @Autowired
  private UserDaoInterface userDao;

  @Autowired
  private TagServiceInterface tagService;

  @Autowired
  private EntityServiceInterface<Status> serviceStatus;

  @Autowired
  private EntityServiceInterface<Priority> servicePriority;

  @Autowired
  private UserServiceInterface serviceUser;

  @Autowired
  private UsersTasksDaoInterface usersTasksDao;

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
    task.setEstimateTime(taskDTO.getEstimateTime());
    task.setPriorityId(taskDTO.getPriorityId());
    task.setParentId(taskDTO.getParentId());
    task.setStatusId(taskDTO.getStatusId());
    task.setTags(taskDTO.getTags());

    task = taskDao.create(task);
    tagService.setTagsToTask(Arrays.asList(taskDTO.getTags()), task.getId());
    return task;
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
    task.setEstimateTime(taskDTO.getEstimateTime());
    task.setPriorityId(taskDTO.getPriorityId());
    task.setParentId(taskDTO.getParentId());
    task.setStatusId(taskDTO.getStatusId());
    task.setTags(taskDTO.getTags());

    taskDao.update(task);
    tagService.deleteTagsOfTask(task.getId());
    tagService.setTagsToTask(Arrays.asList(taskDTO.getTags()), task.getId());

    return false;
  }


  @Override
  public boolean updateCalendarTask(Task task) {
    taskDao.update(task);
    return false;
  }

  @Override
  public List<Task> getPlannedTasks() {
    return taskDao.getPlannedTasks();
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
  public List<TaskTableDTO> getFilteredTasksForTable(int parentId, String[] dates, int[] status,
      int[] priority, int[] tag, boolean planing, int userId) {

    FilterStateWrapper wrapper = new FilterStateWrapper();
    wrapper.setParentId(parentId);
    wrapper.setPriority(priority);
    wrapper.setStatus(status);
    wrapper.setTag(tag);
    wrapper.setPlanned(planing);
    wrapper.setDates(dates);
    List<Integer> targetId = new ArrayList<>();
    List<TaskTreeDTO> treeDTOS = taskDao.findTaskByTree(parentId, userId);
    class Wrape{
      void findLeafs (List<TaskTreeDTO> trees){
        for (TaskTreeDTO dto : trees){
          if (dto.isChildren()) findLeafs(taskDao.findTaskByTree(dto.getId(), userId));
          targetId.add(dto.getId());
        }
      }

  }
    Wrape wrape = new Wrape();
    wrape.findLeafs(treeDTOS);
    wrapper.setIdS(targetId);
    JooqSQLBuilder builder = new JooqSQLBuilder(wrapper);

    // -- remove root-tasks
    List<TaskTableDTO> list = taskDao.getFilteredTasks(builder);
    List<TaskTableDTO> tasksTableDTO = new ArrayList<TaskTableDTO>();
    for(TaskTableDTO taskTableDTO : list){
      if (taskDao.findTaskByTree(taskTableDTO.getId(), userId).size()==0) {
        tasksTableDTO.add(taskTableDTO);
      }
    }

    return tasksTableDTO;
  }

  @Override
  public List<TaskTreeDTO> findTaskByTree(int id, int userId) {
    return taskDao.findTaskByTree(id, userId);
  }

  @Override
  public TaskFullInfoDTO getFullInfo(int id) {
    Task task = taskDao.findOne(id);
    System.out.println(task);
    TaskFullInfoDTO taskDTO = new TaskFullInfoDTO();

    taskDTO.setId(task.getId());
    taskDTO.setName(task.getName());
    taskDTO.setPlanningDate(task.getPlanningDate());
    taskDTO.setDraftPlanning(task.getStartDate());
    taskDTO.setEstimateTime(task.getEstimateTime());
    taskDTO.setSpentTime(task.getSpentTime());
    taskDTO.setLeftTime(task.getLeftTime());
    if (task.getAssignTo() > 0) {
      taskDTO.setAssignTo(serviceUser.findOne(task.getAssignTo()));
    }
    else taskDTO.setAssignTo(null);
    if (task.getStatusId() > 0) {
      taskDTO.setStatus(task.getStatus());
    }
    else taskDTO.setStatus(null);
    if (task.getPriorityId() > 0) {
      taskDTO.setPriority(task.getPriority());
    }
    else taskDTO.setPriority(null);
    if (task.getAuthorId() > 0) {
      taskDTO.setAuthor(serviceUser.findOne(task.getAuthorId()).getName());
    }
    else taskDTO.setAuthor("");

    return taskDTO;
  }

  private Date getFormatDate(String line) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(line);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new Date();
  }

  private Time getTimeFormat(String line) {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    try {
      Date date = sdf.parse(line);
      Calendar calendar = GregorianCalendar.getInstance();
      calendar.setTime(date);

      return new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
          calendar.get(Calendar.SECOND));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return new Time(00, 00, 00);
  }
}