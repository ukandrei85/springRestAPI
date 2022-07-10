package com.endava.springrestapi.controller;

import com.endava.springrestapi.model.Book;
import com.endava.springrestapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAllUsers() {
        return bookService.getAll();
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getById(@PathVariable int id) {
        return bookService.findById(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.PATCH)
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
        return bookService.update(id, bookDetails);
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        return bookService.delete(id);
    }
}
