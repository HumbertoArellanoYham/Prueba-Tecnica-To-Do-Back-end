package com.applicationtodo.task.services.interfaces;

import java.util.Optional;

public interface LoginInterface<T> {
    Optional<T> getUser(String user);

    Optional<T> createUser(T t);
}
