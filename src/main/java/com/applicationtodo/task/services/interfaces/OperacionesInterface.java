package com.applicationtodo.task.services.interfaces;

import java.util.List;
import java.util.Optional;

public interface OperacionesInterface<T> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    Optional<T> findByName(String name);

    Optional<T> update(T t);

    Optional<T> save(T t);

    void deleteById(Integer id);

    void deleteByName(String name);
}
