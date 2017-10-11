package com.softserve.academy.dao.interfaces;

import com.softserve.academy.entity.Tag;
import java.util.List;

public interface TagDaoInterface extends EntityDaoInterface<Tag> {
  List<Tag> getAllByUserId(int userId);

  boolean deleleAllByUserId(int userId);

}
