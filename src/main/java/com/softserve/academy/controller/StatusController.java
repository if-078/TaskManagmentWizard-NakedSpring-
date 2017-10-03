package com.softserve.academy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.entity.Status;
import com.softserve.academy.service.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.softserve.academy.service.StatusService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("statuses")
public class StatusController {

    @Autowired
    ServiceInterface<Status> service;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = "/")
    public String getStatuses() throws SQLException, JsonProcessingException {
        Status status=new Status();
        List list = service.getAll();
        String json = objectMapper.writeValueAsString(list);
        return json;
    }

    @PostMapping(value = "/")
    public Status createStatus(@RequestBody Status status) throws SQLException {
        return service.create(status);
    }

    @GetMapping("/{id}")
    Status getStatus(@PathVariable Integer id) throws SQLException {
        return service.findOne(id);
    }

    @DeleteMapping("/{id}")
    boolean deleteStatus(@PathVariable Integer id) throws SQLException {
        return service.delete(id);
    }

}
