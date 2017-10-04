
package com.softserve.academy.service.implementation;



import com.softserve.academy.dao.implementation.TagDaoimpl;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.service.interfaces.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;



@org.springframework.stereotype.Service
public class TagService implements Service<Tag> {

  @Autowired
  TagDaoimpl dao;

  @Override
  public List<Tag> getAll() {
    throw new UnsupportedOperationException("Not supported yet.");
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
