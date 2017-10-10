package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.TaskDao;
import com.softserve.academy.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.annotation.Annotation;
import java.sql.SQLException;
import java.util.List;
import com.softserve.academy.service.interfaces.EntityService;

@org.springframework.stereotype.Service
public class TaskService implements EntityService<Task> {

  @Autowired
  TaskDao taskDao;

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

  // @Override
  public Class<? extends Annotation> annotationType() {
    return null;
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
   * //@Override public ArrayList<Task> getTasksForToday() { return taskDao.getTasksForToday(); }
   * 
   * //@Override public ArrayList<Task> getTasksByTag(int tagId) { return
   * taskDao.getTasksByTag(tagId); }
   * 
   * //@Override public ArrayList<Task> treeOfTasks(int taskId) { return
   * taskDao.treeOfTasks(taskId); }
   * 
   * //@Override public User getAuthorOfTask(int taskId) { return taskDao.getAuthorOfTask(taskId); }
   * 
   * //@Override public ArrayList<Tag> getTagsOfTask(int taskId) { return
   * taskDao.getTagsOfTask(taskId); }
   * 
   * //@Override public ArrayList<Comment> getCommentsOfTask(int taskId) { return
   * taskDao.getCommentsOfTask(taskId); }
   */

}
