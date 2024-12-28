package com.applicationtodo.task.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TareasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void todasLasTareasPendientesTest() throws Exception {
        String expectedResponse = "[{\"id_tarea\":6,\"nombre\":\"Limpiar formulario\",\"description\":\"Establecer todo el fomulario a estado original\",\"tareaCompletada\":true,\"fechaInicio\":\"2024-12-27\",\"fechaFin\":\"2024-12-28\"}," +
                "{\"id_tarea\":8,\"nombre\":\"Prueba tecnica\",\"description\":\"Realizar la prueba \",\"tareaCompletada\":false,\"fechaInicio\":\"2024-12-27\",\"fechaFin\":\"2024-12-31\"}," +
                "{\"id_tarea\":10,\"nombre\":\"segunda prueba\",\"description\":\"Desarrollar una aplicación ToDo que permita a los usuarios crear y administrar tareas pendientes. La aplicación tendrá una interfaz de usuario simple con la capacidad de agregar, marcar como completada y eliminar tareas.\",\"tareaCompletada\":false,\"fechaInicio\":\"2024-12-26\",\"fechaFin\":\"2025-01-03\"}," +
                "{\"id_tarea\":5,\"nombre\":\"estructura\",\"description\":\"estructurar el proyecto \",\"tareaCompletada\":true,\"fechaInicio\":\"2024-12-27\",\"fechaFin\":\"2024-12-31\"}," +
                "{\"id_tarea\":7,\"nombre\":\"Design\",\"description\":\"Material design para las vistas de los componentes\",\"tareaCompletada\":false,\"fechaInicio\":\"2024-12-27\",\"fechaFin\":\"2024-12-28\"}," +
                "{\"id_tarea\":4,\"nombre\":\"api documentation\",\"description\":\"Agregar la documentacion correspondiente al proyecto desde los inicios, sus tecnologias y todo lo relacionado con ejecutarlo en de manera local\",\"tareaCompletada\":true,\"fechaInicio\":\"2024-12-27\",\"fechaFin\":\"2024-12-31\"}]";

        this.mockMvc.perform(get("/task/all-task"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));
    }

    @Test
    void encontrarTareaPorIdTest() throws Exception{
        String expectedResponse = "{\"id_tarea\":6,\"nombre\":\"Limpiar formulario\",\"description\":\"Establecer todo el fomulario a estado original\",\"tareaCompletada\":true,\"fechaInicio\":\"2024-12-27\",\"fechaFin\":\"2024-12-28\"}";

        this.mockMvc.perform(get("/task/found-task-id/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));
    }

    // Error en el test el nombre no coincide con el de la base de datos
    @Test
    void encontrarTareaPorNombreTest() throws Exception {
        String expectedResponse = "{\"id_tarea\":4,\"nombre\":\"api documentation\",\"description\":\"Agregar la documentacion correspondiente al proyecto desde los inicios, sus tecnologias y todo lo relacionado con ejecutarlo en de manera local\",\"tareaCompletada\":true,\"fechaInicio\":\"2024-12-27\",\"fechaFin\":\"2024-12-31\"}]";

        this.mockMvc.perform(get("/task/found-task-name/apidocumentation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));
    }

}