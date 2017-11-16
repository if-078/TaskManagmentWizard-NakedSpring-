package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.dto.TaskDTO;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskFullInfoDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.dto.mapper.TaskTransformator;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.service.api.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    TaskServiceInterface taskService;

    @Autowired
    TaskTransformator transformator;

    @Autowired
    public TaskController(TaskServiceInterface taskService) {
        this.taskService = taskService;
    }




    @GetMapping("/tree/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<TaskTreeDTO> getTreeSubtask(@PathVariable Integer id){
        return taskService.findTaskByTree(id);
    }

    @GetMapping("/planning")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getPlannedTasks(){return taskService.getPlannedTasks();}

    @PutMapping("/planning")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    boolean updateCalendarTask(@Validated @RequestBody Task task) {
        return taskService.updateCalendarTask(task);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskTableDTO> getFilteredTasks (
            @RequestParam(name="parentid", required = false) int parentId,
            @RequestParam(name="date", required = false) String[] date,
            @RequestParam(name="status", required = false) int[] status,
            @RequestParam(name="priority", required = false) int[] priority,
            @RequestParam(name="tag", required = false) int[] tag){

        return taskService.getFilteredTasksForTable(parentId, date, status, priority, tag);
    }

    @GetMapping("/view/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskFullInfoDTO getFullInfoByUser(@PathVariable Integer id) {
        return taskService.getFullInfo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Validated @RequestBody TaskDTO taskDTO) {return taskService.createTaskByDTO(taskDTO);}

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    boolean updateTask(@Validated @RequestBody TaskDTO taskDTO) {
        return taskService.updateTaskByDTO(taskDTO);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    boolean deleteTask(@PathVariable int id){
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