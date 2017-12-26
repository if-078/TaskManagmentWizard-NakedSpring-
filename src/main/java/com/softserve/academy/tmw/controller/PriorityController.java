package com.softserve.academy.tmw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/priority")
public class PriorityController {

  EntityServiceInterface<Priority> service;

  ObjectMapper objectMapper;

  @Autowired
  public PriorityController(EntityServiceInterface<Priority> service, ObjectMapper objectMapper) {
    this.service = service;
    this.objectMapper = objectMapper;
  }

  @GetMapping
  public List<Priority> getAll() throws SQLException, JsonProcessingException {
    return service.getAll();
  }

  @GetMapping(("/{id}"))
  String get(@PathVariable Integer id) throws SQLException, IOException {
    Priority priority = service.findOne(id);
    String json = objectMapper.writeValueAsString(priority);
    return json;
  }

}
