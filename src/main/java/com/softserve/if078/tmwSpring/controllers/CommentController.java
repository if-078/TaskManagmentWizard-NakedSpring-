package com.softserve.if078.tmwSpring.controllers;


import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.if078.tmwSpring.entities.Comment;
import com.softserve.if078.tmwSpring.services.CommentService;

@RestController
@RequestMapping("/comments")

public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/")
    List<Comment> getAllComments() throws SQLException {
        return commentService.getAll();
    }

    @PostMapping("/")
    Comment createComment(@RequestBody Comment comment)throws SQLException {
        return commentService.create(comment);
    }

    @GetMapping("/{commentid}")
    Comment getComment(@PathVariable Integer commentid)throws SQLException {
        return commentService.get(commentid);
    }

    @PutMapping("/")
    boolean updateComment(@RequestBody Comment comment)throws SQLException {
        return commentService.update(comment);
    }

    @DeleteMapping("/{commentid}")
    boolean deleteComment(@PathVariable Integer commentid) throws SQLException {
        return commentService.delete(commentid);
    }

}
