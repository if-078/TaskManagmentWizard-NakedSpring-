package com.softserve.academy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.entity.Task;
import com.softserve.academy.service.implementation.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.softserve.academy.service.interfaces.EntityService;

@RestController
@RequestMapping("tasks")
public class TaskController {


  EntityService<Task> service;
  ObjectMapper objectMapper;

  @Autowired
  public TaskController(EntityService<Task> service, ObjectMapper objectMapper) {
    this.service = service;
    this.objectMapper = objectMapper;
  }


  @PostMapping
  public Task createTask(@RequestBody Task task) throws SQLException {
    return  service.create(task);
  }

  @PutMapping("/update")
  boolean updateTask(@RequestBody String json) throws SQLException, IOException {
    Task taskObj = objectMapper.readValue(json, Task.class);
    return service.update(taskObj);
  }

  @DeleteMapping("/delete")
  boolean deleteTask(@RequestBody String json) throws SQLException, IOException {
    Task task = new Task();
    task = objectMapper.readValue(json, Task.class);
    return service.delete(task.getId());
  }

  @GetMapping//("/")
  String getAllTasks() throws SQLException, JsonProcessingException {
    List list = service.getAll();
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

  @GetMapping("/one")
  Task getTask(@RequestBody String json) throws SQLException, IOException {
    int id = objectMapper.readValue(json, Integer.class);
    return service.findOne(id);
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
