package com.softserve.academy.tmw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
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

    TaskServiceInterface taskService;

    @Autowired
    public TaskController(TaskServiceInterface taskService) {
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
    public Task createTask(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("/update")
    boolean updateTask(@RequestBody Task task) {
        return taskService.update(task);}

    @DeleteMapping("/{id}")
    boolean deleteTask(@PathVariable int id){
        return taskService.delete(id);
    }

    @GetMapping
    List<Task> getAllTasks() {
       return taskService.getAll();
    }

   @GetMapping("/one/{id}")
   Task getTask(@PathVariable Integer id) {
       return taskService.findOne(id);
   }

   @GetMapping("/today")
   List<Task> getTasksForToday() {
       return taskService.getTasksForToday();
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
}