package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.dto.*;
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
    return taskService.updateCalendarTask(task);
  }

  @GetMapping("/tree/{id}")
  @ResponseStatus(HttpStatus.OK)
  List<TaskTreeDTO> getTreeSubtask(@PathVariable Integer id, @RequestParam(name = "userId", required = false) int userId) {
    return taskService.findTaskByTree(id, userId);
  }

  @GetMapping("/filter")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskTableDTO> getFilteredTasks(
      @RequestParam(name = "parentid", required = false) int parentId,
      @RequestParam(name = "date", required = false) String[] date,
      @RequestParam(name = "status", required = false) int[] status,
      @RequestParam(name = "priority", required = false) int[] priority,
      @RequestParam(name = "tag", required = false) int[] tag) {

    return taskService.getFilteredTasksForTable(parentId, date, status, priority, tag);
  }

  @GetMapping("/view/{id}")
  @ResponseStatus(HttpStatus.OK)
  TaskFullInfoDTO getFullInfoByUser(@PathVariable Integer id) {
    return taskService.getFullInfo(id);
  }

  /*@PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Task createTask(@Validated @RequestBody TaskDTO taskDTO) {
    return taskService.createTaskByDTO(taskDTO);
  }
*/
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

  @PostMapping("/jira-import")
  @ResponseStatus(HttpStatus.CREATED)
  public Task importTask(@Validated @RequestBody TaskJiraDTO taskJiraDTO) {
    return taskService.createTaskByJiraDTO(taskJiraDTO);
  }

  @GetMapping("/id/{key}")
  @ResponseStatus(HttpStatus.OK)
  int getTaskByJiraKey(@PathVariable String key) {
    return taskService.getTaskByJiraKey(key);
  }
}