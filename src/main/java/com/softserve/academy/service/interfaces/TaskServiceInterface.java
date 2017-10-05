package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.Task;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TaskServiceInterface {

    public List<Task> getAll() throws SQLException;

    public Task get(int id) throws SQLException;

    public void update(Task task) throws SQLException;

    public void delete(int id) throws SQLException;

    public void create(Task task) throws SQLException;

    /*public ArrayList<Task> getTaskByStatus(int statusId);

    public ArrayList<Task> getTaskByPriority(int priorityId);

    public ArrayList<Task> getTasksCreatedByUser(int userId);

    public ArrayList<Task> getTasksAssignToUser(int userId);

    public ArrayList<Task> getTasksForToday();

    public ArrayList<Task> getTasksByTag(int tagId);

    public  ArrayList<Task> treeOfTasks(int taskId);

    public User getAuthorOfTask(int taskId);

    public ArrayList<Tag> getTagsOfTask(int taskId);

    public ArrayList<Comment> getCommentsOfTask(int taskId);*/

}