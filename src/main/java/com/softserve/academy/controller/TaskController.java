package com.softserve.academy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.DTO.TaskTableDto;
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
    public Task createTask(@RequestBody Task task) throws SQLException {
        return taskService.create(task);
    }

    @PutMapping("/update")
    boolean updateTask(@RequestBody String json) throws SQLException, IOException {
        Task taskObj = objectMapper.readValue(json, Task.class);
        return taskService.update(taskObj);
    }

    @DeleteMapping("/delete")
    boolean deleteTask(@RequestBody String json) throws SQLException, IOException {
        Task task = new Task();
        task = objectMapper.readValue(json, Task.class);
        return taskService.delete(task.getId());
    }

    @GetMapping
//("/")
   String getAllTasks() throws SQLException, JsonProcessingException {
       List list = taskService.getAll();
       String json = objectMapper.writeValueAsString(list);
       return json;
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
   String getSubtasks(@PathVariable Integer id) throws SQLException, JsonProcessingException {
       List list = taskService.getSubtasks(id);
       String json = objectMapper.writeValueAsString(list);
       return json;
    }

    @GetMapping("/sa")
    public List<TaskTableDto> getFilteredTasks (
            @RequestParam(name="taskid", required = false) int taskId,
            @RequestParam(name="date", required = false) String[] date,
            @RequestParam(name="status", required = false) int[] status,
            @RequestParam(name="priority", required = false) int[] priority,
            @RequestParam(name="tag", required = false) int[] tag){
        System.out.println("SEARCHING");
        return taskService.getFilteredTasksForTable(taskId, date, status, priority, tag);

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