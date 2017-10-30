package com.softserve.academy.service.interfaces;

import com.softserve.academy.dto.dtoentity.TaskFullInfoDTO;
import com.softserve.academy.dto.dtoentity.TaskTreeDTO;
import com.softserve.academy.dto.dtoentity.TaskTableDTO;
import com.softserve.academy.entity.Comment;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;
import java.util.List;

public interface TaskServiceInterface extends EntityServiceInterface<Task> {

  public List<Task> getTasksForToday();

    List<Task> getSprint();

    public List<Tag> getTagsOfTask(int taskId);

  public List<Comment> getCommentsOfTask(int taskId);

  public List<Task> getSubtasks(int id);

  public List<Task> getTasksAssignToUser(int userId);

  List<TaskTreeDTO> findTaskByTree(int id);

  List<TaskTableDTO> findTaskByFilter(int parentId, String[] date, int[] status, int[] priority, int[] tag);

  TaskFullInfoDTO getFullInfo(int id);
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
