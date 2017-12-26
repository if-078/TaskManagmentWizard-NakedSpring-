package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.TagDao;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.service.api.TagServiceInterface;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TagService implements TagServiceInterface {

  @Autowired
  private TagDao tagDao;

  @Override
  public List<Tag> getAll() {
    return tagDao.getAll();
  }

  @Override
  public List<Tag> getAllByProject(int projectId) {
    return tagDao.getAllByProject(projectId);
  }

  @Override
  public List<Tag> getAllByTaskId(int taskId) {
    return tagDao.getAllByTaskId(taskId);
  }

  @Override
  public boolean deleteTagsOfTask(int taskId) {
    return tagDao.deleteTagsOfTask(taskId);
  }

  @Override
  public boolean setTagsToTask(List<Tag> tags, int taskId) {
    return tagDao.setTagsToTask(tags, taskId);
  }

  @Override
  public boolean deleteAllByProject(int projectId) {
    return tagDao.deleteAllByProject(projectId);
  }

  @Override
  public Tag findOne(int id) {

    Optional<Tag> optionalTag = Optional.empty();

    try {
      optionalTag = Optional.of(tagDao.findOne(id));

    } catch (EmptyResultDataAccessException e) {

    }
    return optionalTag.get();
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

}