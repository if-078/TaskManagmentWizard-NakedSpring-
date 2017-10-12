package com.softserve.academy.controller;

import com.softserve.academy.entity.Comment;
import com.softserve.academy.service.interfaces.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    public CommentServiceInterface commentService;

    @GetMapping("/")
    List<Comment> getAllComment() throws SQLException {
        return commentService.getAll();
    }

    @PostMapping("/")
    Comment createComment( @RequestBody Comment comment)throws SQLException {
        return commentService.create(comment);
    }

    @GetMapping("/{id}")
    Comment getComment(@PathVariable Integer id)throws SQLException {
        return commentService.findOne(id);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    boolean updateComment(@RequestBody Comment comment)throws SQLException {
        return commentService.update(comment);
    }

    @DeleteMapping("/{id}")
    boolean deleteComment(@PathVariable Integer id) throws SQLException {
        return commentService.delete(id);
    }

}
