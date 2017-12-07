package com.softserve.academy.tmw.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiraService {
    //private String server = "http://localhost:8585";
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public JiraService() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        //headers.add("Accept", "*/*");
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

    public String getIssues(String uri, String credentials) {
        headers.add("Authorization", "Basic " + credentials);
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody().toString());
        JSONArray issues =new JSONObject(responseEntity.getBody()).getJSONArray("issues");
        List arrayList = new ArrayList();
        for (int i = 0; i < issues.length(); i++) {
            JSONObject jsonobject = issues.getJSONObject(i);

            arrayList.add(jsonobject);
        }
        return responseEntity.getBody();
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


}
