package com.softserve.academy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("status")
public class StatusController {

    @Autowired
    Service<Status> service;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping()
    public String getStatuses() throws SQLException, JsonProcessingException {
        List list = service.getAll();
        String json = objectMapper.writeValueAsString(list);
        return json;
    }

    @PostMapping()
    public Status createStatus(@RequestBody Status status) throws SQLException {
        return service.create(status);
    }

    @GetMapping(value="/one")
    Status getStatus(@RequestBody String json) throws SQLException, IOException {
        int id = objectMapper.readValue(json, Integer.class);
        return service.findOne(id);
    }

    @DeleteMapping("/")
    boolean deleteStatus(@RequestBody String json) throws SQLException, IOException {
        int id = objectMapper.readValue(json, Integer.class);
        return service.delete(id);
    }

    @PutMapping(value = "/")
    boolean updateStatus(@RequestBody String json) throws SQLException, IOException {
        Status statusObj = objectMapper.readValue(json, Status.class);
        return service.update(statusObj);
    }

}
