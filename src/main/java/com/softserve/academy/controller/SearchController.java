package com.softserve.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.DTO.SearchingTransferObject;
import com.softserve.academy.entity.Task;
import com.softserve.academy.service.implementation.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    @PostMapping("/")
    public List<Task> serchTaskByResponse(@RequestBody String json) throws IOException {
        SearchingTransferObject sto;
        ObjectMapper mapper = new ObjectMapper();
        sto = mapper.readValue(json, SearchingTransferObject.class);
        SearchService service = new SearchService(sto);
        return service.getFilteredResult();
    }

}