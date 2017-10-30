package com.softserve.academy.controller;

import com.softserve.academy.entity.Tag;
import com.softserve.academy.service.implementation.TagService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/{uid}/tags")
public class TagController {

  @Autowired
  private TagService tagService;

  @GetMapping
  List<Tag> getTagsByUserId(@PathVariable Integer uid) throws SQLException {
    return tagService.getAllByUserId(uid);
  }

  @PostMapping
  public Tag createTag(@RequestBody Tag tag) throws SQLException {
    return tagService.create(tag);
  }

  @GetMapping("/{id}")
  Tag getTag(@PathVariable Integer id) throws SQLException {
    return tagService.findOne(id);
  }

  @PutMapping
  public boolean updateTag(@RequestBody Tag tag) {
    return tagService.update(tag);
  }

  @DeleteMapping("/{id}")
  public boolean deleteTag(@PathVariable Integer id) {
    return tagService.delete(id);
  }

  @DeleteMapping
  public boolean deleteAllUserTags(@PathVariable Integer uid) {
    return tagService.deleleAllByUserId(uid);
  }
}