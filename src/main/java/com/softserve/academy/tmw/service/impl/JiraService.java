package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dto.TaskJiraDTO;
import com.softserve.academy.tmw.entity.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class JiraService {
    @Autowired
    private TaskService taskService;
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

    public void getIssues(String uri, String credentials) {
        headers.add("Authorization", "Basic " + credentials);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody().toString());
        JSONArray issues = new JSONObject(responseEntity.getBody()).getJSONArray("issues");
        Map rootTasks = new HashMap();
        List subTasks = new ArrayList();
        for (int i = 0; i < issues.length(); i++) {
            JSONObject issue = issues.getJSONObject(i);
            if (issue.getJSONObject("field").getJSONObject("issueType").getString("subtask").equals("false")) {
                TaskJiraDTO rootTaskDto = new TaskJiraDTO();
                rootTaskDto.setName(issue.getJSONObject("field").getString("summary"));
                rootTaskDto.setCreatedDate(issue.getJSONObject("field").getString("created"));
                rootTaskDto.setStatusId(issue.getJSONObject("field").getJSONObject("status").getJSONObject("statusCategory").getInt("id"));
                rootTaskDto.setPriorityId(issue.getJSONObject("field").getJSONObject("priority").getInt("id"));
                rootTaskDto.setJiraKey(issue.getString("key"));
                rootTaskDto.setAssignTo(issue.getInt("assignTo"));
                Task rootTask = taskService.createTaskByJiraDTO(rootTaskDto);

                int rootTaskId = rootTask.getId();
                for (int a = 0; a < issues.length(); a++) {
                    if (issue.getJSONObject("parent").getString("key").equals(rootTaskDto.getJiraKey())) {
                        JSONObject subIssue = issues.getJSONObject(a);
                        TaskJiraDTO subTaskDto = new TaskJiraDTO();
                        subTaskDto.setName(issue.getJSONObject("field").getString("summary"));
                        subTaskDto.setCreatedDate(issue.getJSONObject("field").getString("created"));
                        subTaskDto.setStatusId(issue.getJSONObject("field").getJSONObject("status").getJSONObject("statusCategory").getInt("id"));
                        subTaskDto.setPriorityId(issue.getJSONObject("field").getJSONObject("priority").getInt("id"));
                        subTaskDto.setJiraKey(issue.getString("key"));
                        subTaskDto.setAssignTo(issue.getInt("assignTo"));
                        Task subTask = taskService.createTaskByJiraDTO(subTaskDto);
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


}
