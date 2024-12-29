package com.applicationtodo.task.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "tareas", schema = "public")
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea")
    private Integer id_tarea;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Size(min = 5, max = 3000)
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "tarea_completada", nullable = false)
    private Boolean tareaCompletada;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    Tareas(){}

    public Integer getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Integer id_tarea) {
        this.id_tarea = id_tarea;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Boolean getTareaCompletada() {
        return tareaCompletada;
    }

    public void setTareaCompletada(Boolean tareaCompletada) {
        this.tareaCompletada = tareaCompletada;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "id_tarea=" + id_tarea +
                ", nombre='" + nombre + '\'' +
                ", description='" + description + '\'' +
                ", tareaCompletada=" + tareaCompletada +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
