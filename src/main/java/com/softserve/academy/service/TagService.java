/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.service;

import com.softserve.academy.dao.implementation.TagDaoimpl;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.service.interfaces.ServiceInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oleg
 */
@Service
public class TagService implements ServiceInterface<Tag> {

  @Autowired
  TagDaoimpl dao;

  @Override
  public List<Tag> getAll() {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }


  public List<Tag> getAllByUserId(int userId) {
    return dao.getAllByUserId(userId);
  }

  public boolean deleleAllByUserId(int userId) {
    return dao.deleleAllByUserId(userId);
  }

  @Override
  public Tag findOne(int id) {
    return dao.findOne(id);
  }

  @Override
  public boolean update(Tag entity) {
    return dao.update(entity);
  }

  @Override
  public boolean delete(int id) {
    return dao.delete(id);
  }

  @Override
  public Tag create(Tag entity) {
    return dao.create(entity);
  }

}
