package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.service.impl.CommentService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/comment")
public class CommentController {

  @Autowired
  public CommentService commentService;

  @GetMapping("/")
  List<Comment> getAll() throws SQLException {
    return commentService.getAll();
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  Comment create(@RequestBody Comment comment) throws SQLException {
    return commentService.create(comment);
  }

  @GetMapping("/{id}")
  Comment get(@PathVariable Integer id) throws SQLException {
    return commentService.findOne(id);
  }

  @GetMapping("/task/{id}")
  List<Comment> getCommentsByTaskId(@PathVariable Integer id) throws SQLException {
    return commentService.getCommentsByTaskId(id);
  }

  @PutMapping("/")
  @ResponseStatus(HttpStatus.OK)
  boolean update(@RequestBody Comment comment) throws SQLException {
    return commentService.update(comment);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  boolean delete(@PathVariable Integer id) throws SQLException {
    return commentService.delete(id);
  }

}
