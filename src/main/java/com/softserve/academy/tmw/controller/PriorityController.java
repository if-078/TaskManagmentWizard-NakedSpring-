package com.softserve.academy.tmw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("priority")
public class PriorityController {

  EntityServiceInterface<Priority> service;

  ObjectMapper objectMapper;

  @Autowired
  public PriorityController(EntityServiceInterface<Priority> service, ObjectMapper objectMapper) {
    this.service = service;
    this.objectMapper = objectMapper;
  }

  @GetMapping
  public String getPriorities() throws SQLException, JsonProcessingException {
    List list = service.getAll();
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

  @GetMapping(("/{id}"))
  String getPriorityById(@PathVariable Integer id) throws SQLException, IOException {
    Priority priority = service.findOne(id);
    String json = objectMapper.writeValueAsString(priority);
    return json;
  }

}
