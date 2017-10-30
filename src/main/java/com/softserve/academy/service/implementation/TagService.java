package com.softserve.academy.service.implementation;

import com.softserve.academy.dao.implementation.TagDao;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.service.interfaces.TagServiceInterface;
import java.util.List;
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
  public List<Tag> getAllByUserId(int userId) {
    return tagDao.getAllByUserId(userId);
  }

  @Override
  public boolean deleleAllByUserId(int userId) {
    return tagDao.deleleAllByUserId(userId);
  }

  @Override
  public Tag findOne(int id) {
    Tag tag;
    try {
      return tag = tagDao.findOne(id);
    } catch (EmptyResultDataAccessException e) {
      return tag = new Tag();
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

  public Tag findTagByName(Tag target) { // gain all user tags and found one with name equals as
                                         // target name;
    for (Tag t : getAllByUserId(target.getUserId())) {
      return t;
    }
    return null;
  }

}
