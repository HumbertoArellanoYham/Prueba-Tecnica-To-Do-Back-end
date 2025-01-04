package com.applicationtodo.task.services;

import com.applicationtodo.task.configurations.ConfigurationSettings;
import com.applicationtodo.task.entities.User;
import com.applicationtodo.task.repositories.UserRepository;
import com.applicationtodo.task.services.interfaces.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements LoginInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoderKey;

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUser(String user) {
        return userRepository.getUserForValidateLogin(user);
    }

    @Transactional
    @Override
    public Optional<User> createUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    public String encryptPassword(String password){
        return passwordEncoderKey.encode(password);
    }

    public boolean matchesPassword(String passwordFront, String passwordBd){
        return passwordEncoderKey.matches(passwordFront, passwordBd);
    }
}
