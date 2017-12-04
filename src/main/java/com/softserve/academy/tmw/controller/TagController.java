package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.service.impl.TagService;
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
@RequestMapping("api/tags")
public class TagController {

  @Autowired
  private TagService tagService;

  @GetMapping
  List<Tag> getTagsByUserId() throws SQLException {
    // return tagService.getAllByUserId(uid);
    return tagService.getAll();
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Tag createTag(@RequestBody Tag tag) throws SQLException {
    return tagService.create(tag);
  }

  @GetMapping("/{id}")
  public Tag getTag(@PathVariable Integer id) throws SQLException {
    return tagService.findOne(id);
  }

  @PutMapping()
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public boolean updateTag(@RequestBody Tag tag) {
    return tagService.update(tag);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public boolean deleteTag(@PathVariable Integer id) {
    return tagService.delete(id);
  }


}
