package com.fundatec.tcc.service.usuario;

import com.fundatec.tcc.controller.exceptions.InvalidCredentialsException;
import com.fundatec.tcc.model.User;
import com.fundatec.tcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public User loginUser(String username, String password) {
        User existingUser = findByUsername(username);
        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return existingUser;
        } else {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
}

