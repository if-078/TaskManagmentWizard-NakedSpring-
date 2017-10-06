
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
@RequestMapping("tags")
public class TagController {

  @Autowired
  private TagService service;

  @GetMapping("/all/{id}")
  List<Tag> getTagsByUserId(@PathVariable Integer Id) throws SQLException {
    return service.getAllByUserId(Id);
  }

  @PostMapping("/")
  public Tag createTag(@RequestBody Tag tag) throws SQLException {
    return service.create(tag);
  }

  @GetMapping("/{id}")
  public Tag findTag(@PathVariable Integer id) throws SQLException {
    return service.findOne(id);
  }

  @PutMapping("/")
  public boolean updateTag(@RequestBody Tag tag) {
    return service.update(tag);
  }

  @DeleteMapping("/{tId}")
  public boolean deleteTag(@PathVariable Integer tId) {
    return service.delete(tId);
  }

  @DeleteMapping("/")
  public boolean deleteAllUserTags(@PathVariable Integer tId) {
    return service.deleleAllByUserId(tId);
  }
}
