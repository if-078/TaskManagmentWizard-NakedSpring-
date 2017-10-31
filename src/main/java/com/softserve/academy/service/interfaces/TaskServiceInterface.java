package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.Comment;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;
import java.util.List;

public interface TaskServiceInterface extends EntityServiceInterface<Task> {
    List<Task> getTasksForToday();

    List<Task> getSprint();

    List<Tag> getTagsOfTask(int taskId);

    List<Comment> getCommentsOfTask(int taskId);

    List<Task> getSubtasks(int id);

    List<Task> getTasksAssignToUser(int userId);

  /*
   * public ArrayList<Task> getTaskByStatus(int statusId);
   *
   * public ArrayList<Task> getTaskByPriority(int priorityId);
   *
   * public ArrayList<Task> getTasksCreatedByUser(int userId);
   *
   * public ArrayList<Task> getTasksForToday();
   *
   * public ArrayList<Task> getTasksByTag(int tagId);
   *
   * public User getAuthorOfTask(int taskId);
   */

}