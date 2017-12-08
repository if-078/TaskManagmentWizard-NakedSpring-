package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.JiraCredential;
import com.softserve.academy.tmw.service.impl.JiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List getProjects(@RequestBody JiraCredential jiraCredential) {
        List json = jiraService.getProjects(jiraCredential.getUrl(), jiraCredential.getCreds());
        return json;
    }

    @PostMapping("/get-issues")
    @ResponseStatus(HttpStatus.OK)
    public void getIssues(@RequestBody JiraCredential jiraCredential) {
        jiraService.getIssues(jiraCredential.getUrl(), jiraCredential.getCreds());

    }
}
