package com.endava.springrestapi.controller;

import com.endava.springrestapi.model.User;
import com.endava.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable int id) {
        return userService.findById(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
       return  userService.update(id, userDetails);
    }


}

