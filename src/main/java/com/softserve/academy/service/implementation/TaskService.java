package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.interfaces.TaskDaoInterface;
import com.softserve.academy.dao.interfaces.UserDaoInterface;
import com.softserve.academy.dto.dtoentity.TaskFullInfoDTO;
import com.softserve.academy.dto.dtoentity.TaskTreeDTO;
import com.softserve.academy.dto.dtoentity.TaskTableDTO;
import com.softserve.academy.entity.*;
import com.softserve.academy.service.interfaces.EntityServiceInterface;
import com.softserve.academy.service.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class TaskService implements TaskServiceInterface {

  @Autowired
  TaskDaoInterface taskDao;

  @Autowired
  UserDaoInterface userDao;

  @Autowired
  EntityServiceInterface<Status> service;

  @Autowired
  EntityServiceInterface<Priority> servicePriority;

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
  public List<TaskTableDTO> findTaskByFilter(int parentId, String[] date, int[] status, int[] priority, int[] tag){


    for(int oneTag : tag){
      System.out.println("tag_id = " + oneTag);
    }

    List<Task> allTask = taskDao.getAll();
    List<User> allUser = userDao.getAll();

    List<Task> filteredTask = new ArrayList<>();


    //-- filter by parent id
    for(Task task : allTask){
      if(parentId==task.getParentId()){
        filteredTask.add(task);
      }
    }
    allTask = filteredTask;
    filteredTask = new ArrayList<>();


    //-- filtering by Date
    // -------------------


    //-- filtering by Status
    if(status.length != 0) {
      for (int selectedStatus : status) {
        for (Task task : allTask) {
          if (selectedStatus == task.getStatusId()) {
            filteredTask.add(task);
          }
        }
      }
      allTask = filteredTask;
    filteredTask = new ArrayList<>();
    }


    //-- filtering by Priority
    if(priority.length != 0) {
      for (int selectedPriority : priority) {
        for (Task task : allTask) {
          if (selectedPriority == task.getPriorityId()) {
            filteredTask.add(task);
          }
        }
      }
      allTask = filteredTask;
      filteredTask = new ArrayList<>();
    }




    //-- convert from Task to TaskTableDTO
    List<TaskTableDTO> taskTableDTO = new ArrayList<>();
    for(Task task : allTask){
        TaskTableDTO taskTable = new TaskTableDTO();
        taskTable.setId(task.getId());
        taskTable.setName(task.getName());
        taskTable.setCreatedDate(task.getCreatedDate());
        taskTable.setStartDate(task.getStartDate());
        taskTable.setEstimateTime(task.getEstimateTime());
        taskTable.setAssignTo(""+task.getAssignTo());
        taskTable.setStatus("" + task.getStatusId());
        taskTable.setPriority("" + task.getPriorityId());
        taskTableDTO.add(taskTable);
    }


    for(int i=0; i<allUser.size(); i++){
      User user = allUser.get(i);
      for(int j=0; j<taskTableDTO.size(); j++){
        if((""+user.getId()).equals(taskTableDTO.get(j).getAssignTo())){
          String name = user.getName();
          taskTableDTO.get(j).setAssignTo(name);
        }
      }
    }

    List<Status> allStatus = service.getAll();
    for(int i=0; i<allStatus.size(); i++){
      Status stat = allStatus.get(i);
      for(int j=0; j<taskTableDTO.size(); j++){
        if((""+stat.getId()).equals(taskTableDTO.get(j).getStatus())){
          String strStatus = stat.getName();
          taskTableDTO.get(j).setStatus(strStatus);
        }
      }
    }

    List<Priority> allPriority = servicePriority.getAll();
    for(int i=0; i<allPriority.size(); i++){
      Priority prior = allPriority.get(i);
      for(int j=0; j<taskTableDTO.size(); j++){
        if((""+prior.getId()).equals(taskTableDTO.get(j).getPriority())){
          String strPrior = prior.getName();
          taskTableDTO.get(j).setPriority(strPrior);
        }
      }
    }

    return taskTableDTO;
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
    taskDTO.setAssignTo(""+task.getAssignTo());
    taskDTO.setStatus("" + task.getStatusId());
    taskDTO.setPriority("" + task.getPriorityId());

    List<User> allUser = userDao.getAll();
    for(int i=0; i<allUser.size(); i++){
      User user = allUser.get(i);
        if((""+user.getId()).equals(taskDTO.getAssignTo())){
          String name = user.getName();
          taskDTO.setAssignTo(name);
      }
    }

    List<Status> allStatus = service.getAll();
    for(int i=0; i<allStatus.size(); i++){
      Status stat = allStatus.get(i);
        if((""+stat.getId()).equals(taskDTO.getStatus())){
          String strStatus = stat.getName();
          taskDTO.setStatus(strStatus);
        }

    }

    List<Priority> allPriority = servicePriority.getAll();
    for(int i=0; i<allPriority.size(); i++){
      Priority prior = allPriority.get(i);
        if((""+prior.getId()).equals(taskDTO.getPriority())){
          String strPrior = prior.getName();
          taskDTO.setPriority(strPrior);
        }
    }



    return taskDTO;
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
   * //@Override public ArrayList<Task> getTasksByTag(int tagId) { return
   * taskDao.getTasksByTag(tagId); }
   * 
   * //@Override public ArrayList<Task> treeOfTasks(int taskId) { return
   * taskDao.treeOfTasks(taskId); }
   * 
   * //@Override public User getAuthorOfTask(int taskId) { return taskDao.getAuthorOfTask(taskId); }
   */

}
