package com.softserve.academy.controller;

import com.softserve.academy.entity.Task;
import com.softserve.academy.service.interfaces.TaskServiceInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskServiceInterface taskDao;
    /*@Autowired
    TaskDao taskDao;*/


    @PostMapping("/")
    void createTask(@RequestBody Task task) throws SQLException {
        taskDao.create(task);
    }

    @PutMapping("/")
    void updateTask(@RequestBody Task task) throws SQLException {
        taskDao.update(task);
    }

    @DeleteMapping("/{id}")
    void deleteTask(@PathVariable int id) throws SQLException {
        taskDao.delete(id);
    }

    @GetMapping("/")
    List<Task> getAllTasks() throws SQLException {
        return taskDao.getAll();
    }

    @GetMapping("/{id}")
    Task getTask(@PathVariable int id) throws SQLException {
        return taskDao.get(id);
    }

    /*@GetMapping("/tag/{id}")
    List<Task> tasksByTag(@PathVariable int id) {
        return taskDao.getTasksByTag(id);
    }

    @GetMapping("/priority/{id}")
    List<Task> tasksByPriority(@PathVariable int id) {
        return taskDao.getTaskByPriority(id);
    }

    @GetMapping("/status/{id}")
    List<Task> tasksByStatus(@PathVariable int id) {
        return taskDao.getTaskByStatus(id);
    }

    @GetMapping("/today")
    List<Task> tasksForToday() {
        return taskDao.getTasksForToday();
    }

    @GetMapping("/assign_to/{id}")
    List<Task> assignTo(@PathVariable int id) {
        return taskDao.getTasksAssignToUser(id);
    }

    @GetMapping("/created_by/{id}")
    List<Task> createdBy(@PathVariable int id) {
        return taskDao.getTasksCreatedByUser(id);
    }

    @GetMapping("/tree_of/{id}")
    List<Task> treeOfTasks(@PathVariable int id) {
        return taskDao.treeOfTasks(id);
    }

    @GetMapping("/author_of_task/{id}")
    User authorOfTssk(@PathVariable int id) {
        return taskDao.getAuthorOfTask(id);
    }

    @GetMapping("/tags_of/{id}")
    List<Tag> tagsOfTask(@PathVariable int id) {
        return taskDao.getTagsOfTask(id);
    }

    @GetMapping("/comments_of/{id}")
    List<Comment> commentsOfTask(@PathVariable int id) {
        return taskDao.getCommentsOfTask(id);
    }*/

}