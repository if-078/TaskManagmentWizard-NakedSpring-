package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.dto.*;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.entity.Task;
import java.util.List;

public interface TaskServiceInterface extends EntityServiceInterface<Task> {

  List<Tag> getTagsOfTask(int taskId);

  List<Comment> getCommentsOfTask(int taskId);

  List<Task> getSubtasks(int id);

  List<Task> getTasksAssignToUser(int userId);

  List<TaskTableDTO> getFilteredTasksForTable(int parentId, String[] dates, int[] status,
      int[] priority, int[] tag);

  List<TaskTreeDTO> findTaskByTree(int id, int userId);

  TaskFullInfoDTO getFullInfo(int id);

  Task createTaskByDTO(TaskDTO taskDTO);

  boolean updateTaskByDTO(TaskDTO taskDTO);

  boolean updateCalendarTask(Task task);

  List<Task> getPlannedTasks();

  Task createTaskByJiraDTO(TaskJiraDTO taskJiraDTO);

  int getTaskByJiraKey(String key);
}