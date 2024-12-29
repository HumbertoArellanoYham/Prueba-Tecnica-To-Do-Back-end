package com.applicationtodo.task.repositories;

import com.applicationtodo.task.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="select * from login_users where usuario = ?1", nativeQuery = true)
    Optional<User> getUserForValidateLogin(String name);
}
