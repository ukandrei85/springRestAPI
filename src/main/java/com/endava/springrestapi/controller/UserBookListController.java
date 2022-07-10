package com.endava.springrestapi.controller;

import com.endava.springrestapi.model.UserBookList;
import com.endava.springrestapi.service.UserBooksListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userBookList")
public class UserBookListController {
    @Autowired
    private UserBooksListService userBooksListService;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestParam("userId") int userId,@RequestParam("bookId") int bookId) {
         userBooksListService.create(userId,bookId);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<UserBookList> getAll() {
        return userBooksListService.getAll();
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.GET)
    public ResponseEntity<UserBookList> getById(@PathVariable int id) {
        return userBooksListService.findById(id);
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        return userBooksListService.delete(id);
    }
}
