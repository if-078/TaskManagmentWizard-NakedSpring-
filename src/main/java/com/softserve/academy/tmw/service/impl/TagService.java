package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.TagDao;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.service.api.TagServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements TagServiceInterface {

    @Autowired
    private TagDao tagDao;

//    @Override
//    public List<Tag> getAll() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    @Override
    public List<Tag> getAll() {
     return   tagDao.getAll();
    }

    @Override
    public List<Tag> getAllByUserId(int userId) {
        return tagDao.getAllByUserId(userId);
    }

    @Override
    public boolean deleleAllByUserId(int userId) {
        return tagDao.deleteAllByUserId(userId);
    }

    @Override
    public Tag findOne(int id) {
        try {
            return tagDao.findOne(id);
        } catch (EmptyResultDataAccessException e) {
            return new Tag();
        }

    }

    @Override
    public boolean update(Tag entity) {
        return tagDao.update(entity);
    }

    @Override
    public boolean delete(int id) {
        return tagDao.delete(id);
    }

    @Override
    public Tag create(Tag entity) {
        try {
            entity = tagDao.create(entity);
        } catch (DataAccessException e) {
            return new Tag();
        }
        return entity;
    }

    public Tag findTagByName(Tag target) {
        return getAllByUserId(target.getUserId()).stream().filter(s -> s.getName().contains(target.getName())).findAny().orElse(new Tag());
    }

    public boolean addTagToTask(int[] tags, int taskId) {
        return tagDao.setTagsToTask(tags, taskId);
    }




    /*public static void addTagsToTask() {
        int[] tagsList = new int[2];

        tagsList[0] = 1;
        tagsList[1] = 2;
        System.out.println("qwe");
        tagDao.setTagsToTask(tagsList, 2);
        System.out.println("rty");

    }

    public static void main(String[] args) {
        //addTagsToTask();


        System.out.println(tagDao.findOne(2));
        *//*int[] tagsList = new int[2];
        for (int i = 0; i < tagsList.length; i++) {
            tagsList[i] = i + 1;
        }
        for (int i = 0; i < tagsList.length; i++) {
            System.out.println(tagsList[i]);
        }
        tagsList[0] = 1;
        tagsList[1] = 2;
        System.out.println(tagDao);
        tagDao.setTagsToTask(tagsList, 3);*//*
    }*/

}