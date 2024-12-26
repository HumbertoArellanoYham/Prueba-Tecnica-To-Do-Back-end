package com.applicationtodo.task.services;

import com.applicationtodo.task.entities.Tareas;
import com.applicationtodo.task.repositories.TareaRepository;
import com.applicationtodo.task.services.interfaces.OperacionesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
public class TareaService implements OperacionesInterface<Tareas> {

    @Autowired
    private TareaRepository tareaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Tareas> findAll() {
        return tareaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Tareas> findById(Integer id) {
        return tareaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Tareas> findByName(String name) {
        return tareaRepository.findByTaskName(name);
    }

    @Transactional
    @Override
    public Optional<Tareas> update(Tareas tareas) {
        return Optional.of(tareaRepository.save(tareas));
    }

    @Transactional
    @Override
    public Optional<Tareas> save(Tareas tareas) {
        return Optional.of(tareaRepository.save(tareas));
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        tareaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        tareaRepository.eliminarPorNombre(name);
    }
}
