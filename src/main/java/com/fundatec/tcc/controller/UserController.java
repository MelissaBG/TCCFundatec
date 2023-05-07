package com.fundatec.tcc.controller;
import com.fundatec.tcc.controller.exceptions.NoUsersFoundException;
import com.fundatec.tcc.controller.exceptions.UsernameAlreadyExistsException;
import com.fundatec.tcc.model.User;
import com.fundatec.tcc.service.usuario.UserService;
import com.fundatec.tcc.controller.exceptions.InvalidCredentialsException;
import com.fundatec.tcc.controller.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() throws NoUsersFoundException {
        List<User> users = userService.findAll();
        if(users.isEmpty()) {
            throw new NoUsersFoundException("No users found");
        } else {
            return users;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.findById(id);
        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    @PostMapping("/salvar")
    public ResponseEntity<User> createUser(@RequestBody User user) throws UsernameAlreadyExistsException {
        if(userService.findByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Username " + user.getUsername() + " already exists");
        }
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User existingUser = userService.findById(id);
        if(existingUser == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        user.setId(existingUser.getId());
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        User existingUser = userService.findById(id);
        if(existingUser == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User authenticatedUser = userService.loginUser(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    }

}