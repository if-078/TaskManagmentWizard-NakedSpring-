package com.softserve.academy.service.implementation;

import com.softserve.academy.DTO.SearchingTransferObject;
import com.softserve.academy.dao.implementation.Dao;
import com.softserve.academy.dao.implementation.PriorityDao;
import com.softserve.academy.entity.Priority;
import com.softserve.academy.entity.Status;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private StatusService statusService;
    private PriorityDao priorityDao;
    private TagService tagService;
    private TaskService taskService;
    private SearchingTransferObject searchingTransferObject;


    public SearchService(SearchingTransferObject searchingTransferObject) {
        this.searchingTransferObject = searchingTransferObject;
    }

    @Autowired
    public void setStatusService(StatusService statusService) {
        this.statusService = statusService;
    }

    @Autowired
    public void setPriorityDao(PriorityDao priorityDao) {
        this.priorityDao = priorityDao;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    private List<Task> filterTasks(SearchingTransferObject sto) {
        List<Tag> userTags = filterTags(sto);
        List<Task> res = new ArrayList<>();
        for (Tag tag : userTags){
            res.addAll(taskService.getTasksByTag(tag.getId()));
        }
        return res;
    }

    private List<Tag> filterTags(SearchingTransferObject sto){
        List<Tag> res = new ArrayList<>();
        for (Tag t : sto.getTargetTags()){
            res.add(tagService.findTagByName(t));
        }
        return res;
    }

    private List<Status> filterStatuses (SearchingTransferObject sto){
        List<Status> res = new ArrayList<>();
        for (Status status : sto.getTargetStatuses()){
            res.add(statusService.findByName(status));
        }
        return res;
    }

    private List<Priority> filterPriorities(SearchingTransferObject sto){
        List<Priority> res = new ArrayList<>(4);
        for (Priority p : sto.getTargetPriorities()){
            res.add(priorityDao.findByName(p));
        }
        return res;
    }
}