package com.endava.springrestapi.controller;

import com.endava.springrestapi.data.api.UserBookListDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.service.UserBooksListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userBookList")
public class UserBookListController {
    @Autowired
    private UserBooksListService userBooksListService;
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> adduserBooksList(@RequestBody UserBookListDto user) {
        MessageResponse message = userBooksListService.createUserBookList(user);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<UserBookListDto>> getAllUserBookList() {
        List<UserBookListDto> userBookListDto = userBooksListService.getAllUserBookList();
        return new ResponseEntity<>(userBookListDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserBookListDto> getUserById(@PathVariable("id") Integer id) {
        UserBookListDto userBookListDto = userBooksListService.getASingleUserBookList(id);
        return new ResponseEntity<>(userBookListDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<MessageResponse> updateUserBookList(@PathVariable Integer id, @RequestBody UserBookListDto userBookListDto) {
        MessageResponse message =userBooksListService.updateUserBookList(id,userBookListDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Integer id) {
        MessageResponse message = userBooksListService.deleteUserBookList(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
