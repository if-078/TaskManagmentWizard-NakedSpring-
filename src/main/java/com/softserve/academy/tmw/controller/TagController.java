package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.service.impl.TagService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tags")
public class TagController {

  @Autowired
  private TagService tagService;

  @GetMapping
  List<Tag> getAll() {
    return tagService.getAll();
  }

  @GetMapping("/projectId")
  List<Tag> getByProject(@PathVariable Integer projectId) {
    return tagService.getAllByProject(projectId);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public Tag create(@RequestBody Tag tag) {
    return tagService.create(tag);
  }

  @GetMapping("/{id}")
  public Tag get(@PathVariable Integer id) {
    return tagService.findOne(id);
  }

  @PutMapping()
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public boolean update(@RequestBody Tag tag) {
    return tagService.update(tag);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public boolean delete(@PathVariable Integer id) {
    return tagService.delete(id);
  }


}
