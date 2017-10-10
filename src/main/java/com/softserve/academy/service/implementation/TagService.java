
package com.softserve.academy.service.implementation;



import com.softserve.academy.dao.implementation.TagDao;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.service.interfaces.TagServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TagService implements TagServiceInterface {

  @Autowired
  private TagDao tagDao;

  @Override
  public List<Tag> getAll() {
    throw new UnsupportedOperationException("Not supported yet.");
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
    return tagDao.findOne(id);
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
    return tagDao.create(entity);
  }

}
