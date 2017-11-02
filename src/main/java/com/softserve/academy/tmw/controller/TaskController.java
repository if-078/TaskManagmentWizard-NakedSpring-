package com.softserve.academy.tmw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.service.api.TaskServiceInterface;
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


    @GetMapping("/tree/{id}")
    List<TaskTreeDTO> getTreeSubtask(@PathVariable Integer id){
        return taskService.findTaskByTree(id);
    }

    @GetMapping("/filter")
    public List<TaskTableDTO> getFilteredTasks (
            @RequestParam(name="parentid", required = false) int parentId,
            @RequestParam(name="date", required = false) String[] date,
            @RequestParam(name="status", required = false) int[] status,
            @RequestParam(name="priority", required = false) int[] priority,
            @RequestParam(name="tag", required = false) int[] tag){
        return taskService.getFilteredTasksForTable(parentId, date, status, priority, tag);
    }

    @GetMapping("/view/{id}")
    TaskFullInfoDTO getFullInfoByUser(@PathVariable Integer id) {
        return taskService.getFullInfo(id);
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

    @DeleteMapping("/{id}")
    boolean deleteTask(@PathVariable int id){
        return taskService.delete(id);
    }

    @GetMapping
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
}