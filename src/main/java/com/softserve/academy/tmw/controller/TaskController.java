package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.dto.TaskDTO;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.service.api.TaskServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

  TaskServiceInterface taskService;

  @Autowired
  public TaskController(TaskServiceInterface taskService) {
    this.taskService = taskService;
  }


  @GetMapping("/planning/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Task getPlannedTask(@PathVariable Integer id) {
    return taskService.findOne(id);
  }

  @GetMapping("/planning")
  @ResponseStatus(HttpStatus.OK)
  public List<Task> getPlannedTasks() {
    return taskService.getPlannedTasks();
  }

  @PutMapping("/planning")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean updateCalendarTask(@Validated @RequestBody Task task) {

    System.out.println("=== Start Update Task ===");
    System.out.println("taskId = " + task.getId());
    System.out.println("taskName = " + task.getName());

    boolean isUpdateTask = taskService.updateCalendarTask(task);
    return isUpdateTask;
  }

  @GetMapping("/tree/{id}")
  @ResponseStatus(HttpStatus.OK)
  List<TaskTreeDTO> getTreeSubtask(@PathVariable Integer id, @RequestParam(name = "userId", required = false) int userId) {
    return taskService.findTaskByTree(id, userId);
  }

  @GetMapping("/filter")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskTableDTO> getFilteredTasks(
      @RequestParam(name = "parentId", required = false) int parentId,
      @RequestParam(name = "date", required = false) String[] date,
      @RequestParam(name = "status", required = false) int[] status,
      @RequestParam(name = "priority", required = false) int[] priority,
      @RequestParam(name = "tag", required = false) int[] tag,
      @RequestParam(name = "planing", required = false) boolean planing,
      @RequestParam(name = "userId", required = false) int userId)
  {

    List<TaskTableDTO> tasksTableDTO = taskService.getFilteredTasksForTable(parentId, date, status, priority, tag, planing, userId);


    for(TaskTableDTO task : tasksTableDTO){
      System.out.println("==========================");
      System.out.println("id = " + task.getId());
      System.out.println("name = " + task.getName());
      System.out.println("createDate = " + task.getCreatedDate());
      System.out.println("planningDate = " + task.getPlanningDate());
      System.out.println("startDate = " + task.getStartDate());
      System.out.println("endDate = " + task.getEndDate());
      System.out.println("estimateTime = " + task.getEstimateTime());
      System.out.println("spentTime = " + task.getSpentTime());
      System.out.println("leftTime = " + task.getLeftTime());
      System.out.println("assignTo = " + task.getAssignTo());
      System.out.println("statusId = " + task.getStatusId());
      System.out.println("priorityId = " + task.getPriorityId());
      System.out.println("parentId = " + task.getParentId());
      System.out.println("authorId = " + task.getAuthorId());
      System.out.println("projectId = " + task.getProjectId());
      System.out.println("Assign Name = " + task.getAssign());
      System.out.println("Status Name = " + task.getStatus());
      System.out.println("Priority Name = " + task.getPriority());
      System.out.println("==========================");
    }

    return tasksTableDTO;
  }

  @GetMapping("/view/{id}")
  @ResponseStatus(HttpStatus.OK)
  TaskFullInfoDTO getFullInfoByUser(@PathVariable Integer id) {
    return taskService.getFullInfo(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@Validated @RequestBody TaskDTO taskDTO) {
    return taskService.createTaskByDTO(taskDTO);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean updateTask(@Validated @RequestBody TaskDTO taskDTO) {
    return taskService.updateTaskByDTO(taskDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean deleteTask(@PathVariable int id) {
    return taskService.delete(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  List<Task> getAllTasks() {
    return taskService.getAll();
  }

  @GetMapping("/one/{id}")
  @ResponseStatus(HttpStatus.OK)
  Task getTask(@PathVariable Integer id) {
    return taskService.findOne(id);
  }

  @GetMapping("/{id}/tags")
  @ResponseStatus(HttpStatus.OK)
  List<Tag> getTagsOfTask(@PathVariable Integer id) {
    return taskService.getTagsOfTask(id);
  }

  @GetMapping("/{id}/comments")
  @ResponseStatus(HttpStatus.OK)
  List<Comment> getCommentsOfTask(@PathVariable Integer id) {
    return taskService.getCommentsOfTask(id);
  }

  @GetMapping("/{id}/subtasks")
  @ResponseStatus(HttpStatus.OK)
  List<Task> getSubtasks(@PathVariable Integer id) {
    return taskService.getSubtasks(id);
  }
}