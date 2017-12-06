package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.JiraCredential;
import com.softserve.academy.tmw.service.impl.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/jira")
public class JiraController {


    private JiraService jiraService;

    @Autowired
    public JiraController(JiraService jiraService) {
        this.jiraService = jiraService;
    }

    @PostMapping("/get-projects")
    @ResponseStatus(HttpStatus.OK)
    public String getProjects(@RequestBody JiraCredential jiraCredential) {
        return jiraService.get(jiraCredential.getUrl(), jiraCredential.getCreds());
    }
}
