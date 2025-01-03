# API REST para gestionar las tareas

## Tecnologías utilizadas y herramientas
  * Java 17
  * Spring boot 3.4
  * Intellij Idea community
  * Postman
  * PGAdmin base de datos
  * PostGresSQL 

## Operaciones:
  * Crear
  * Leer
  * Actualizar
  * Eliminar

## Arquitectura utilizada basada en capas
Me permite abstraer y encapsular el codigo, esto solo para la actividad técnica si se continua el desarrollo 
se debe optar por re-estructurar el proyecto basado en funcionalidad como lo muesta la documentación de Spring Framework.

<img width="434" alt="Screen Shot 2024-12-27 at 3 19 00" src="https://github.com/user-attachments/assets/dab83fe3-3e42-4071-a49d-d82ecbb3ec11" />

## Testing 
Las pruebas unitarias estan para probar solo la capa inferior, donde Spring maneja la solicitud HTTP entrante y la entrega a su controlador. De esa manera, se utiliza casi toda la pila completa y el código se llamará exactamente de la misma manera que si estuviera procesando una solicitud HTTP real, pero sin el costo de iniciar el servidor.

   *  Utilizamos Spring MockMvc para simular datos
   *  Se injecta @AutoConfigureMockMvc en la clase del test

Otro punto a tomar en cuenta es ejecutar los test en otro puerto, asi logramos que no interfiera con la ejecucion de la aplicacion principal:
   *  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

## Encriptación de contraseñas
   * Se utilizo la herramienta que nos proporciona spring security BCryptPasswordEncoder, utilize PasswordEncoder en mi UserService para codificar la contraseña durante el proceso de registro del usuario.
     
![Screen Shot 2024-12-29 at 17 23 22](https://github.com/user-attachments/assets/b6ca14dd-03d8-4416-8748-ef2cc2e4e292)


## Base de datos diagrama
![Screen Shot 2024-12-27 at 3 24 18](https://github.com/user-attachments/assets/2099af70-dabb-493b-9c19-f963f37d34b3)

## Rutas / URL's
Tareas
  * http://localhost:8080/task/all-task
  * http://localhost:8080/task/found-task-id/{id}
  * http://localhost:8080/task/found-task-name/{name}
  * http://localhost:8080/task/save-task/
  * http://localhost:8080/task/patch-task/
  * http://localhost:8080/task/delete-task-id/{id}
  * http://localhost:8080/task/delte-task-name/{name}

## Ejecutar el proyecto backend localmente
Tomando en cuenta que ya se tiene ejecutando el frontend correctamente(si no puedes leer el readme del proyecto) aqui muestro
los pasos para ejecutar localmente el backend de spring boot con java:

  1.- Installar el IDE intellij idea se puede hacer desde la pagina oficial
  
  2.- Una vez instalado se necesita clonar el proyecto de la siguiente manera:
  
      * git clone https://github.com/HumbertoArellanoYham/Prueba-Tecnica-To-Do-Back-end

  3.- Abrir el proyecto en intellij idea

  4.- Descargar pgadmin4 desde la pagina oficial y aqui no se nesesita hacer mucho ya que en las entidades de
      spring estan mapeadas a la tabla(se puede visualizar en el archivo application.properties) lo que si 
      nesesitamos es crear la base de datos nadamas.

  5.- Para mapear a la base de datos abrimos el archivo application.properties y descomentamos la linea siguiente:
  
          * spring.jpa.hibernate.ddl-auto=create
  
      comentamos la siguiente:
          * spring.jpa.hibernate.ddl-auto=update
      esto solo cuando ejecutamos el backend por primera vez para comunicar la base de datos con la entidad establecida y crear las 
      columnas asociadas.

  6.- Ejecutar el proyecto, para esto simplemente vamos al archivo principal como lo identificamos por que estara con la anotación @SpringBootApplication
      presionamos en ejecutar y listo estar corriendo localmente en el puerto 8080.

Ahora ya se tiene el frontend, el backend y la base de datos ejecutandose correctamente, los proyectos son independietes uno del otro y se comunican por 
medio de los servicios y los controladores en formato json(importante tomar en cuenta las configuraciones de los CORS).
        

