package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dto.TaskJiraDTO;
import com.softserve.academy.tmw.entity.Task;
import net.rcarz.jiraclient.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JiraClientService {
    private String jiraUrl = "https://tmw-sveta.atlassian.net";
    private String jiraUserName = "ovcharuksveta@ukr.net";
    private String jiraUserPassword = "svetasveta";

    //Spring http client
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;
    private BasicCredentials creds = new BasicCredentials(jiraUserName, jiraUserPassword);
    private JiraClient jira = new JiraClient(jiraUrl, creds);


    JiraClientService() {
//        //http client
//        this.rest = new RestTemplate();
//        this.headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        //headers.add("Accept", "*/*");
//        headers.add("Access-Control-Allow-Origin", "*");
//        //jira client lib

        //ovcharuksveta@ukr.net "svetasveta"
        //"https://tmw-sveta.atlassian.net"
    }

    public String getJiraUrl() {
        return jiraUrl;
    }

    public void setJiraUrl(String jiraUrl) {
        this.jiraUrl = jiraUrl;
    }

    public String getJiraUserName() {
        return jiraUserName;
    }

    public void setJiraUserName(String jiraUserName) {
        this.jiraUserName = jiraUserName;
    }

    public String getJiraUserPassword() {
        return jiraUserPassword;
    }

    public void setJiraUserPassword(String jiraUserPassword) {
        this.jiraUserPassword = jiraUserPassword;
    }

    public RestTemplate getRest() {
        return rest;
    }

    public void setRest(RestTemplate rest) {
        this.rest = rest;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public BasicCredentials getCreds() {
        return creds;
    }

    public void setCreds(BasicCredentials creds) {
        this.creds = creds;
    }

    public JiraClient getJira() {
        return jira;
    }

    public void setJira(JiraClient jira) {
        this.jira = jira;
    }

    public String getProjectsJson(String uri, String credentials) {
        headers.add("Authorization", "Basic " + credentials);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody().toString());
        return responseEntity.getBody();

    }


    public TaskJiraDTO getIssue(String key) throws JiraException {
        Issue issue = jira.getIssue("FP-1");
        System.out.println(issue.getSummary());
        System.out.println(issue.getAssignee().getEmail());
        // System.out.println(issue.getStatus().); //воно повертає стрінг, нам для таски треба інт
        System.out.println(issue.getPriority().getId()); //теж треба зі стрінга в інт
        System.out.println(issue.getKey());
        //parent id
        System.out.println(issue.getSubtasks());
        System.out.println(issue.toString());

        return new TaskJiraDTO();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


    public List getProjects() throws JiraException {
        String projects;
        List<Project> list = jira.getProjects();
        return list;
    }

    public static void main(String[] args) throws JiraException {
        JiraClientService jiraClientService = new JiraClientService();
        jiraClientService.setJiraUrl("https://tmw-sveta.atlassian.net");
        jiraClientService.setJiraUserPassword("ovcharuksveta@ukr.net");
        jiraClientService.setJiraUserName("svetasveta");
        jiraClientService.getIssue("FP-1");
        System.out.println(jiraClientService.getJira());
        System.out.println(jiraClientService.getProjects().get(0));



    }
}



