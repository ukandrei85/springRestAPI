package com.endava.springrestapi.controller;

import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.service.UserService;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> addUser(@RequestBody UserDto user) {
        MessageResponse message = userService.createUser(user);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = userService.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        UserDto user = userService.getASingleUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<MessageResponse> updateUser(@PathVariable Integer id, @RequestBody UserDto userDetails) {
        MessageResponse updateUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Integer id) {
        MessageResponse deleteUser = userService.deleteUser(id);
        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    }

}

