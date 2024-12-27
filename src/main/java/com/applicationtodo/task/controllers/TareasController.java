package com.applicationtodo.task.controllers;

import com.applicationtodo.task.entities.Tareas;
import com.applicationtodo.task.services.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/task")
public class TareasController {

    @Autowired
    private TareaService tareaService;

    @GetMapping(value = "/all-task")
    public ResponseEntity<List<Tareas>> todasLasTareasPendientes(){
        return new ResponseEntity<>(tareaService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/found-task-id/{id}")
    public ResponseEntity<Tareas> encontrarTareaPorId(@PathVariable(value = "id") Integer id){
        Optional<Tareas> wasFound = tareaService.findById(id);

        return wasFound.map((task) -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> null);
    }

    @GetMapping(value = "/found-task-name/{name}")
    public ResponseEntity<Tareas> encontrarTareaPorNombre(@PathVariable(value = "name") String nombre){
        Optional<Tareas> wasFound = tareaService.findByName(nombre);

        return wasFound.map((task) -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> null);
    }

    @PostMapping(value = "/save-task")
    public ResponseEntity<Tareas> guardarTarea(@RequestBody Tareas tarea){
        Optional<Tareas> saveTask = tareaService.save(tarea);

        return saveTask.map((taskSaveInDB) -> new ResponseEntity<>(taskSaveInDB, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PatchMapping(value = "/patch-task")
    public ResponseEntity<Tareas> actualizarTarea(@RequestBody Tareas tarea){
        Optional<Tareas> updateTask = tareaService.update(tarea);

        return updateTask.map((taskSaveInDB) -> new ResponseEntity<>(taskSaveInDB, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    // Eliminar tarea dependiendo el parametro, cumplir con todas las especificaciones correspondientes.
    @DeleteMapping(value = "/delete-task-id/{id}")
    public ResponseEntity<?> eliminarTaskPorId(@PathVariable(value = "id") Integer id){
        tareaService.deleteById(id);

        Optional<Tareas> wasRemove = tareaService.findById(id);

        if(wasRemove.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/delete-task-name/{name}")
    public ResponseEntity<?> eliminarTaskPorName(@PathVariable(value = "name") String name){
        tareaService.deleteByName(name);

        Optional<Tareas> wasRemove = tareaService.findByName(name);

        return wasRemove.map((taskPresent) -> new ResponseEntity<>(HttpStatus.BAD_REQUEST))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));
    }

}
