
package com.softserve.academy.controller;

import com.softserve.academy.entity.Tag;
import com.softserve.academy.service.implementation.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user/{uId}/tags")
public class TagController {

  @Autowired
  private TagService service;

  @GetMapping(value = "/all")
  public List<Tag> getTagsByUserId(@PathVariable Integer uId) {
    return service.getAllByUserId(uId);
  }

  @PostMapping(value = "/")
  public Tag createTag(@RequestBody Tag tag) {
    return service.create(tag);
  }

  @GetMapping("/{tId}")
  public Tag findTag(@PathVariable Integer tId) {
    return service.findOne(tId);
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
