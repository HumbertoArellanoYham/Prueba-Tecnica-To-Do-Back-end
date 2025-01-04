package com.applicationtodo.task.services;

import com.applicationtodo.task.entities.Tareas;
import com.applicationtodo.task.repositories.TareaRepository;
import com.applicationtodo.task.services.interfaces.OperacionesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Scope(value = "singleton")
public class TareaService implements OperacionesInterface<Tareas> {

    private static final Logger logger = LoggerFactory.getLogger(TareaService.class);

    @Autowired
    private TareaRepository tareaRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "tareas", key = "'all'")
    @Override
    public List<Tareas> findAll() {
        return tareaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "tareas", key = "#id")
    @Override
    public Optional<Tareas> findById(Integer id) {
        return tareaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "tareas", key = "#name")
    @Override
    public Optional<Tareas> findByName(String name) {
        return tareaRepository.findByTaskName(name);
    }

    @Transactional
    @CachePut(cacheNames = "tareas", key = "#tareas.id_tarea")
    @CacheEvict(cacheNames = "tareas", allEntries = true)
    @Override
    public Optional<Tareas> update(Tareas tareas) {
        logger.info("Actualizando tarea con id: {}", tareas.getId_tarea());
        Optional<Tareas> updatedTask = Optional.of(tareaRepository.save(tareas));
        logger.info("Tarea actualizada: {}", updatedTask);
        return updatedTask;
    }

    @Transactional
    @CachePut(cacheNames = "tareas", key = "#tareas.id_tarea")
    @CacheEvict(cacheNames = "tareas", allEntries = true)
    @Override
    public Optional<Tareas> save(Tareas tareas) {
        return Optional.of(tareaRepository.save(tareas));
    }

    @Transactional
    @CacheEvict(cacheNames = "tareas", key = "#id", allEntries = true)
    @Override
    public void deleteById(Integer id) {
        tareaRepository.deleteById(id);
    }

    @Transactional
    @CacheEvict(cacheNames = "tareas", allEntries = true)
    @Override
    public void deleteByName(String name) {
        tareaRepository.eliminarPorNombre(name);
    }
}
