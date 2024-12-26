package com.applicationtodo.task.repositories;

import com.applicationtodo.task.entities.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tareas, Integer> {
    @Query(value = "select * from tareas where nombre = ?1", nativeQuery = true)
    Optional<Tareas> findByTaskName(String name);

    @Modifying
    @Query(value = "delete from tareas where nombre = ?1", nativeQuery = true)
    void eliminarPorNombre(String name);
}
