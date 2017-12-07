package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.entity.JiraCredential;
import com.softserve.academy.tmw.service.impl.JiraClientService;
import net.rcarz.jiraclient.JiraException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jira")
public class JiraController {


    private JiraClientService jiraClientService;

    @Autowired
    public JiraController(JiraClientService jiraClientService) {

        this.jiraClientService=jiraClientService;
    }

    @PostMapping("/get-projects")
    @ResponseStatus(HttpStatus.OK)
    public String getProjects(@RequestBody JiraCredential jiraCredential) throws JiraException {


        return jiraClientService.getProjectsJson(jiraCredential.getUrl(),jiraCredential.getCreds());
    }

   /* @PostMapping("/get-issues")
    @ResponseStatus(HttpStatus.OK)
    public String getIssues(@RequestBody JiraCredential jiraCredential, String key) throws JiraException {

    }*/
}
