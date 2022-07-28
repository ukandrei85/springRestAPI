package com.endava.springrestapi.controller;

import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> addUser(@RequestBody  UserDto user) {

        MessageResponse message = userService.createUser(user);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = userService.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer user_id) {
        UserDto user = userService.getASingleUser(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/{user_id}", method = RequestMethod.PATCH)
    public ResponseEntity<MessageResponse> updateUser(@PathVariable Integer user_id, @RequestBody UserDto userDetails) {
        MessageResponse updateUser = userService.updateUser(user_id, userDetails);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> delete(@PathVariable Integer user_id) {
        MessageResponse deleteUser = userService.deleteUser(user_id);
        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    }

}

