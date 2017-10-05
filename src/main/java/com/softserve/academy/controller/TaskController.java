package com.softserve.academy.controller;

import com.softserve.academy.entity.Task;
import com.softserve.academy.service.implementation.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

  @Autowired
  TaskService taskService;
  /*
   * @Autowired TaskDao taskService;
   */


  @PostMapping("/")
  void createTask(@RequestBody Task task) throws SQLException {
    taskService.create(task);
  }

  @PutMapping("/")
  void updateTask(@RequestBody Task task) throws SQLException {
    taskService.update(task);
  }

  @DeleteMapping("/{id}")
  void deleteTask(@PathVariable int id) throws SQLException {
    taskService.delete(id);
  }

  @GetMapping("/")
  List<Task> getAllTasks() throws SQLException {
    return taskService.getAll();
  }

  @GetMapping("/{id}")
  Task getTask(@PathVariable int id) throws SQLException {
    return taskService.findOne(id);
  }

  /*
   * @GetMapping("/tag/{id}") List<Task> tasksByTag(@PathVariable int id) { return
   * taskService.getTasksByTag(id); }
   * 
   * @GetMapping("/priority/{id}") List<Task> tasksByPriority(@PathVariable int id) { return
   * taskService.getTaskByPriority(id); }
   * 
   * @GetMapping("/status/{id}") List<Task> tasksByStatus(@PathVariable int id) { return
   * taskService.getTaskByStatus(id); }
   * 
   * @GetMapping("/today") List<Task> tasksForToday() { return taskService.getTasksForToday(); }
   * 
   * @GetMapping("/assign_to/{id}") List<Task> assignTo(@PathVariable int id) { return
   * taskService.getTasksAssignToUser(id); }
   * 
   * @GetMapping("/created_by/{id}") List<Task> createdBy(@PathVariable int id) { return
   * taskService.getTasksCreatedByUser(id); }
   * 
   * @GetMapping("/tree_of/{id}") List<Task> treeOfTasks(@PathVariable int id) { return
   * taskService.treeOfTasks(id); }
   * 
   * @GetMapping("/author_of_task/{id}") User authorOfTssk(@PathVariable int id) { return
   * taskService.getAuthorOfTask(id); }
   * 
   * @GetMapping("/tags_of/{id}") List<Tag> tagsOfTask(@PathVariable int id) { return
   * taskService.getTagsOfTask(id); }
   * 
   * @GetMapping("/comments_of/{id}") List<Comment> commentsOfTask(@PathVariable int id) { return
   * taskService.getCommentsOfTask(id); }
   */

}
