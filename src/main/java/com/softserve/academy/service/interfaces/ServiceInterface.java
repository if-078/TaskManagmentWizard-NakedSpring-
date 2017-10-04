package com.softserve.academy.service.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ServiceInterface<E> {
  List<E> getAll();

  E findOne(int id);

  boolean update(E entity);

  boolean delete(int id);

  E create(E entity);
}
