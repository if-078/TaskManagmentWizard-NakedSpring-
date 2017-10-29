package com.softserve.academy.controller;

import com.softserve.academy.entity.Comment;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;
import com.softserve.academy.service.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

  TaskServiceInterface taskService;

  @Autowired
  public TaskController(TaskServiceInterface taskService) {
    this.taskService = taskService;
  }

  @PostMapping
  public Task createTask(@RequestBody Task task) {
    return  taskService.create(task);
  }

  @PutMapping
  boolean updateTask(@RequestBody Task task) {
    return taskService.update(task);
  }

  @DeleteMapping("/{id}")
  boolean deleteTask(@PathVariable Integer id){
    return taskService.delete(id);
  }

  @GetMapping
  List<Task> getAllTasks() {
    return taskService.getAll();
  }

  @GetMapping("/{id}")
  Task getTask(@PathVariable Integer id) {
    return taskService.findOne(id);
  }

  @GetMapping("/today")
  List<Task> getTasksForToday() {
    return taskService.getTasksForToday();
  }

  @GetMapping("/sprint")
  List<Task> getSprint() {
    return taskService.getSprint();
  }

  @GetMapping("/{id}/tags")
  List<Tag> getTagsOfTask(@PathVariable Integer id) {
    return taskService.getTagsOfTask(id);
  }

  @GetMapping("/{id}/comments")
  List<Comment> getCommentsOfTask(@PathVariable Integer id) {
    return taskService.getCommentsOfTask(id);
  }

  @GetMapping("/{id}/subtasks")
  List<Task> getSubtasks(@PathVariable Integer id) {
    return taskService.getSubtasks(id);

  }

  @GetMapping("/assign_to/{id}")
  List<Task> assignTo(@PathVariable Integer id){
    System.out.println("asigin_to");
    return taskService.getTasksAssignToUser(id);
  }

  //for tree
  @GetMapping("/assign_to/{userId}/parent/{parentId}")
  List<Task> getTasksByUserAndParent(@PathVariable Integer userId, @PathVariable Integer parentId){
    return taskService.getTasksByUserAndParent(userId, parentId);
  }

  @GetMapping("/child/{id}")
  boolean taskHasChild(@PathVariable Integer id) {
    return taskService.taskHasChild(id);
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
