package com.softserve.academy.service.interfaces;

import java.util.List;

public interface EntityServiceInterface<E> {
    List<E> getAll();

    E findOne(int id);

    boolean update(E entity);

    boolean delete(int id);

    E create(E entity);
}
