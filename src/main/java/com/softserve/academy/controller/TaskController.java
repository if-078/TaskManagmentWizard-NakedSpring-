package com.softserve.academy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.entity.Task;
import com.softserve.academy.service.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

  ObjectMapper objectMapper;
  TaskServiceInterface taskService;

  @Autowired
  public TaskController(ObjectMapper objectMapper, TaskServiceInterface taskService) {
    this.objectMapper = objectMapper;
    this.taskService = taskService;
  }

  @PostMapping
  public Task createTask(@RequestBody Task task) {
    System.out.println(" -- create task ---");
    return  taskService.create(task);
  }

  @PutMapping
  boolean updateTask(@RequestBody Task task) {
    System.out.println("-- update -- ");
    return taskService.update(task);
  }

  @DeleteMapping("/{id}")
  boolean deleteTask(@PathVariable Integer id){
    System.out.println("-- delete task ---");
    return taskService.delete(id);
  }
  @GetMapping
  List<Task> getAllTasks() {
    System.out.println("task controller");
    return taskService.getAll();
  }

  @GetMapping("/one")
  Task getTask(@RequestBody String json) throws SQLException, IOException {
    int id = objectMapper.readValue(json, Integer.class);
    return taskService.findOne(id);
  }

  @GetMapping("/today")
  String getTasksForToday() throws SQLException, JsonProcessingException {
    List list = taskService.getTasksForToday();
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

  @GetMapping("/sprint")
  String getSprint() throws SQLException, JsonProcessingException {
    List list = taskService.getSprint();
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

  @GetMapping("/{id}/tags")
  String getTagsOfTask(@PathVariable Integer id) throws SQLException, JsonProcessingException {
    List list = taskService.getTagsOfTask(id);
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

  @GetMapping("/{id}/comments")
  String getCommentsOfTask(@PathVariable Integer id) throws SQLException, JsonProcessingException {
    List list = taskService.getCommentsOfTask(id);
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

  @GetMapping("/{id}/subtasks")
  List<Task> getSubtasks(@PathVariable Integer id) {
    System.out.println("--- sub tasks -- ");
    return taskService.getSubtasks(id);

  }

  @GetMapping("/assign_to/{id}")
  String assignTo(@PathVariable Integer id) throws JsonProcessingException {
    List list = taskService.getTasksAssignToUser(id);
    String json = objectMapper.writeValueAsString(list);
    return json;
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
   * @GetMapping("/assign_to/{id}") List<Task> assignTo(@PathVariable int id) { return
   * taskService.getTasksAssignToUser(id); }
   * 
   * @GetMapping("/created_by/{id}") List<Task> createdBy(@PathVariable int id) { return
   * taskService.getTasksCreatedByUser(id); }
   * 
   * @GetMapping("/author_of_task/{id}") User authorOfTssk(@PathVariable int id) { return
   * taskService.getAuthorOfTask(id); }
   */

}
