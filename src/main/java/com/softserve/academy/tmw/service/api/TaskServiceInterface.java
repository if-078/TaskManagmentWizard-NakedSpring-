package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.dto.TaskDTO;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
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
      int[] priority, int[] tag, boolean planing, int userId);

  List<TaskTreeDTO> findTaskByTree(int id, int userId);

  TaskFullInfoDTO getFullInfo(int id);

  Task createTaskByDTO(TaskDTO taskDTO);

  boolean updateTaskByDTO(TaskDTO taskDTO);

  boolean updateCalendarTask(Task task);

  boolean deletePlanning(int id);

  List<Task> getPlannedTasks();
}