package com.softserve.academy.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface EntityDao<E> {
  List<E> getAll();

  E findOne(int id);

  boolean update(E entity);

  boolean delete(int id);

  E create(E entity);
}
