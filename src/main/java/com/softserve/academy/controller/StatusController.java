package com.softserve.academy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.softserve.academy.service.interfaces.EntityServiceInterface;

@RestController
@RequestMapping("status")
public class StatusController {

  EntityServiceInterface<Status> service;

  ObjectMapper objectMapper;

  @Autowired
  public StatusController(EntityServiceInterface<Status> service, ObjectMapper objectMapper) {
    this.service = service;
    this.objectMapper = objectMapper;
  }

  @GetMapping(value = "/all")
  public String getStatuses() throws SQLException, JsonProcessingException {
    List list = service.getAll();
    String json = objectMapper.writeValueAsString(list);
    return json;
  }

  @PostMapping()
  public Status createStatus(@RequestBody Status status) throws SQLException {
    return service.create(status);
  }

  @GetMapping(value = "/one")
  Status getStatus(@RequestBody String json) throws SQLException, IOException {
    int id = objectMapper.readValue(json, Integer.class);
    return service.findOne(id);
  }

  @DeleteMapping("/del")
  boolean deleteStatus(@RequestBody String json) throws SQLException, IOException {
    Status status = new Status();
    status = objectMapper.readValue(json, Status.class);
    return service.delete(status.getId());
  }

  @PutMapping(value = "/update")
  boolean updateStatus(@RequestBody String json) throws SQLException, IOException {
    Status statusObj = objectMapper.readValue(json, Status.class);
    return service.update(statusObj);
  }

  @GetMapping(value = "/{id}")
  String getStatusById(@PathVariable Integer id) throws SQLException, IOException {
    Status status = service.findOne(id);
    String json = objectMapper.writeValueAsString(status);
    return json;
  }

}
