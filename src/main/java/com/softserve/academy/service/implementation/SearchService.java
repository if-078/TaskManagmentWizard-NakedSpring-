package com.softserve.academy.service.implementation;

import com.softserve.academy.DTO.SearchingTransferObject;
import com.softserve.academy.dao.implementation.PriorityDao;
import com.softserve.academy.entity.Priority;
import com.softserve.academy.entity.Status;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {

    private StatusService statusService;
    private PriorityDao priorityDao;
    private TagService tagService;
    private TaskService taskService;
    private SearchingTransferObject sto;


    public SearchService(SearchingTransferObject searchingTransferObject) {
        this.sto = searchingTransferObject;
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

    public List<Task> getFilteredResult() {
        return thirdPrepareFiltering();
    }

    private List<Task> firstPreparedFiltering() {
        List<Tag> userTags = filterTags();
        List<Task> res = new ArrayList<>();
        for (Tag tag : userTags) {
            res.addAll(taskService.getTasksByTag(tag.getId()));
        }
        return res.stream().filter(task -> task.getAssign_to() == sto.getId()).collect(Collectors.toList());
    }

    private List<Task> secondPrepareFiltering() {
        List<Task> tasks = firstPreparedFiltering();
        List<Priority> priorities = filterPriorities();
        List<Task> res = new ArrayList<>();
        for (Priority p : priorities) {
            Stream<Task> stream = tasks.stream().filter(task -> task.getPriority_id() == p.getId());
            res.addAll(stream.collect(Collectors.toList()));
        }
        return res;
    }

    private List<Task> thirdPrepareFiltering() {
        List<Task> tasks = secondPrepareFiltering();
        List<Status> statuses = filterStatuses();
        List<Task> res = new ArrayList<>();
        for (Status status : statuses) {
            Stream<Task> stream = tasks.stream().filter(task -> task.getStatus_id() == status.getId());
            res.addAll(stream.collect(Collectors.toList()));
        }
        return res;
    }

    private List<Tag> filterTags() {
        List<Tag> res = new ArrayList<>();
        for (Tag t : sto.getTargetTags()) {
            res.add(tagService.findTagByName(t));
        }
        return res;
    }

    private List<Status> filterStatuses() {
        List<Status> res = new ArrayList<>();
        for (Status status : sto.getTargetStatuses()) {
            res.add(statusService.findByName(status));
        }
        return res;
    }

    private List<Priority> filterPriorities() {
        List<Priority> res = new ArrayList<>(4);
        for (Priority p : sto.getTargetPriorities()) {
            res.add(priorityDao.findByName(p));
        }
        return res;
    }
}