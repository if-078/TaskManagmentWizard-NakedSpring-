package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.JiraIntegrationDao;
import com.softserve.academy.tmw.dto.TaskJiraDTO;
import com.softserve.academy.tmw.entity.JiraCredential;
import com.softserve.academy.tmw.entity.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JiraService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private JiraIntegrationDao jiraIntegrationDao;
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public JiraService() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Access-Control-Allow-Origin", "*");

    }

    public List getProjects(String uri, String credentials) {
        headers.add("Authorization", "Basic " + credentials);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody().toString());
        JSONArray jsonArray = new JSONArray(responseEntity.getBody());
        String name;
        String key;
        List arrayList = new ArrayList();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            name = jsonobject.getString("name");
            key = jsonobject.getString("key");
            Map map = new HashMap();
            map.put("name", name);
            map.put("key", key);
            arrayList.add(map);
        }
        return arrayList;
    }

    public void getIssues(JiraCredential credentials) {
        headers.add("Authorization", "Basic " + credentials.getCreds());
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(credentials.getUrl(), HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        TaskJiraDTO project = new TaskJiraDTO();
        project.setJiraKey(credentials.getProjectKey());
        project.setName(credentials.getProjectName());
        try {
            Task projectTask = jiraIntegrationDao.create(dtoToTask(project));
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }
        JSONArray issues = new JSONObject(responseEntity.getBody()).getJSONArray("issues");
        for (int i = 0; i < issues.length(); i++) {
            JSONObject issue = issues.getJSONObject(i);
            if (issue.getJSONObject("fields").getJSONObject("issuetype").getBoolean("subtask") == false) {
                TaskJiraDTO rootTaskDto = new TaskJiraDTO();
                rootTaskDto.setName(issue.getJSONObject("fields").getString("summary"));
                rootTaskDto.setCreatedDate(issue.getJSONObject("fields").getString("created"));
                rootTaskDto.setStatusId(issue.getJSONObject("fields").getJSONObject("status").getJSONObject("statusCategory").getInt("id"));
                rootTaskDto.setPriorityId(issue.getJSONObject("fields").getJSONObject("priority").getInt("id"));
                rootTaskDto.setJiraKey(issue.getString("key"));
                if (issue.getJSONObject("fields").optJSONObject("assignee") != null) {
                    if (issue.getJSONObject("fields").getJSONObject("assignee").get("emailAddress").equals(credentials.getName()) || issue.getJSONObject("fields").getJSONObject("assignee").get("key").equals(credentials.getName())) {
                        rootTaskDto.setAssignTo(credentials.getUserId());
                    } else {
                        rootTaskDto.setAssignTo(0);
                    }

                }
                Task rootTask = taskService.createTaskByJiraDTO(rootTaskDto);
                int rootTaskId = rootTask.getId();
                for (int a = 0; a < issues.length(); a++) {
                    JSONObject subIssue = issues.getJSONObject(a);
                    if (issue.getJSONObject("fields").getJSONObject("issuetype").getBoolean("subtask") == true) {
                        if (subIssue.getJSONObject("fields").getJSONObject("parent").getString("key").equals(rootTaskDto.getJiraKey())) {
                            TaskJiraDTO subTaskDto = new TaskJiraDTO();
                            subTaskDto.setName(subIssue.getJSONObject("fields").getString("summary"));
                            subTaskDto.setCreatedDate(subIssue.getJSONObject("fields").getString("created"));
                            subTaskDto.setStatusId(subIssue.getJSONObject("fields").getJSONObject("status").getJSONObject("statusCategory").getInt("id"));
                            subTaskDto.setPriorityId(subIssue.getJSONObject("fields").getJSONObject("priority").getInt("id"));
                            subTaskDto.setJiraKey(subIssue.getString("key"));
                            if ((subIssue.getJSONObject("fields").getJSONObject("assignee").get("emailAddress").equals(credentials.getName()) || issue.getJSONObject("fields").getJSONObject("assignee").get("key").equals(credentials.getName()))) {
                                rootTaskDto.setAssignTo(credentials.getUserId());
                            } else {
                                rootTaskDto.setAssignTo(0);
                            }

                            Task subTask = jiraIntegrationDao.create(dtoToTask(subTaskDto));
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Task dtoToTask(TaskJiraDTO taskJiraDTO) {
        Task task = new Task();
        task.setName(taskJiraDTO.getName());
        task.setAssignTo(taskJiraDTO.getAssignTo());
        task.setPriorityId(taskJiraDTO.getPriorityId());
        task.setStatusId(taskJiraDTO.getStatusId());
        task.setJiraKey(taskJiraDTO.getJiraKey());
        task.setParentId(taskJiraDTO.getParentId());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
        if (taskJiraDTO.getCreatedDate() != null) {
            try {
                task.setCreatedDate(dateFormat.parse(taskJiraDTO.getCreatedDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return task;
    }
}
