package com.softserve.academy.DTO;

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

    public void setStatuses(String[] statuses) {
        this.statuses = statuses;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setPriorities(String[] priorities) {
        this.priorities = priorities;
    }

    public SearchingTransferObject() {

    }

    public int getId() {
        return id;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public String[] getTags() {
        return tags;
    }

    public String[] getPriorities() {
        return priorities;
    }

    public List<Tag> getTargetTags (){
        List <Tag> res = getEntitiesFromNames(Tag.class, tags);
        res.forEach(tag->tag.setUserId(id));
        return res;
    }

    public List<Status> getTargetStatuses (){
        return getEntitiesFromNames(Status.class, statuses);
    }

    public List<Priority> getTargetPriorities (){
        return getEntitiesFromNames(Priority.class, priorities);
    }

    public  <T extends NamedEntity> List<T> getEntitiesFromNames(Class<T> tClass, String[] strings){
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