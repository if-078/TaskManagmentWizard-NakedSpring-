
package com.softserve.academy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.entity.Priority;
import com.softserve.academy.entity.Status;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.service.implementation.TagService;
import java.sql.SQLException;
import java.util.List;

import com.softserve.academy.service.interfaces.EntityServiceInterface;
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
@RequestMapping("tag")
public class TagController {


  EntityServiceInterface<Tag> service;

  ObjectMapper objectMapper;

  @Autowired
  public TagController(EntityServiceInterface<Tag> service, ObjectMapper objectMapper) {
    this.service = service;
    this.objectMapper = objectMapper;
  }

  @GetMapping
  public String getTags() throws SQLException, JsonProcessingException {
    List list = service.getAll();
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

}
