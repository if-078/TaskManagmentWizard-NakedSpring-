
package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.Tag;
import java.util.List;

/**
 *
 * @author Oleg
 */
public interface TagInterface {
  List<Tag> getAll();

  Tag findOne(int id);

  boolean update(Tag entity);

  boolean delete(int id);

  Tag create(Tag entity);
}
