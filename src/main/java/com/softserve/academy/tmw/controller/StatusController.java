package com.softserve.academy.tmw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.entity.Status;
import com.softserve.academy.tmw.service.api.EntityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    @GetMapping
    public List<Status> getStatuses() throws SQLException, JsonProcessingException {
        return service.getAll();

    }

    @PostMapping
    public Status createStatus(@RequestBody Status status) throws SQLException {
        return service.create(status);
    }

    @DeleteMapping
    boolean deleteStatus(@RequestBody String json) throws SQLException, IOException {
    Status status = new Status();
    status = objectMapper.readValue(json, Status.class);
    return service.delete(status.getId());
  }

    @PutMapping
    boolean updateStatus(@RequestBody String json) throws SQLException, IOException {
        Status statusObj = objectMapper.readValue(json, Status.class);
        return service.update(statusObj);
    }

}