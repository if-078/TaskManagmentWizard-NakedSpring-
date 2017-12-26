package com.softserve.academy.tmw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.entity.Status;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/status")
public class StatusController {

  EntityServiceInterface<Status> service;

  ObjectMapper objectMapper;

  @Autowired
  public StatusController(EntityServiceInterface<Status> service, ObjectMapper objectMapper) {
    this.service = service;
    this.objectMapper = objectMapper;
  }

  @GetMapping
  public List<Status> get() throws SQLException, JsonProcessingException {
    return service.getAll();

  }

  @PostMapping
  public Status create(@RequestBody Status status) throws SQLException {
    return service.create(status);
  }

  @DeleteMapping
  boolean delete(@RequestBody String json) throws SQLException, IOException {
    Status status = new Status();
    status = objectMapper.readValue(json, Status.class);
    return service.delete(status.getId());
  }


}