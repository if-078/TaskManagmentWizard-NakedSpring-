package com.softserve.academy.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softserve.academy.entity.NamedEntity;
import com.softserve.academy.entity.Priority;
import com.softserve.academy.entity.Status;
import com.softserve.academy.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class SearchingTransferObject {
    private int id;
    private String[] statuses;
    private String[] tags;
    private String[] priorities;

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String[] statuses) {
        this.statuses = statuses;
    }

    public void setTag(String[] tags) {
        this.tags = tags;
    }

    public void setPriority(String[] priorities) {
        this.priorities = priorities;
    }

    public SearchingTransferObject() {

    }

    public int getId() {
        return id;
    }

    public String[] getStatus() {
        return statuses;
    }

    public String[] getTag() {
        return tags;
    }

    public String[] getPriority() {
        return priorities;
    }

    public List<Tag> getTargetTags (){
        List <Tag> res = getEntitiesFromNames(Tag.class, tags);
        res.forEach(tag->tag.setUserId(id));
        return res;
    }

    @JsonIgnore
    public List<Status> getTargetStatuses (){
        return getEntitiesFromNames(Status.class, statuses);
    }

    @JsonIgnore
    public List<Priority> getTargetPriorities (){
        return getEntitiesFromNames(Priority.class, priorities);
    }

    @JsonIgnore
    private   <T extends NamedEntity> List<T> getEntitiesFromNames(Class<T> tClass, String[] strings){
        List<T> res = new ArrayList<>();
        for (String s : strings){
            T t = null;
            try {
                t = tClass.newInstance();
                t.setName(s);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            res.add(t);
        }
        return res;
    }
}