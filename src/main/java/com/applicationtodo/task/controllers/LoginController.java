package com.applicationtodo.task.controllers;

import com.applicationtodo.task.configurations.ConfigurationSettings;
import com.applicationtodo.task.configurations.TokenProvider;
import com.applicationtodo.task.entities.User;
import com.applicationtodo.task.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create-user")
    public ResponseEntity<User> crearUsuario(@Valid @RequestBody User registrarUsuario){
        User userSecurity = new User();
        userSecurity.setUsuario(registrarUsuario.getUsuario());
        userSecurity.setPassword(userService.encryptPassword(registrarUsuario.getPassword())
        );

        Optional<User> usuarioRegistradoCorrectamente = userService.createUser(userSecurity);

        return usuarioRegistradoCorrectamente.map(userLogin ->
                new ResponseEntity<>(userLogin, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/logged-in")
    public ResponseEntity<Boolean> seEncuentraRegistrado(@Valid @RequestBody User iniciarSession){
        Optional<User> bdUserLogin = userService.getUser(iniciarSession.getUsuario());

        boolean isFound;
        if(bdUserLogin.isPresent()){
            isFound = userService.matchesPassword(iniciarSession.getPassword(), bdUserLogin.get().getPassword());
        } else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return isFound ? new ResponseEntity<>(true, HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
