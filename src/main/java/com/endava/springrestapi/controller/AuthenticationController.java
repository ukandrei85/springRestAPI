package com.endava.springrestapi.controller;

import com.endava.springrestapi.data.api.AuthenticationDto;
import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> addAuthentication(@RequestBody AuthenticationDto authDto) {
        MessageResponse message = authService.createAuthentication(authDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
