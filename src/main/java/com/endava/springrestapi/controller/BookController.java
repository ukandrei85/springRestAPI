package com.endava.springrestapi.controller;

import com.endava.springrestapi.data.api.BookDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> addBook(@RequestBody BookDto bookDto) {
        MessageResponse message = bookService.createBook(bookDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookDto> getUserById(@PathVariable("id") Integer id) {
        BookDto bookDto = bookService.getASingleBook(id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<MessageResponse> updateBook(@PathVariable Integer id, @RequestBody BookDto bookDetails) {
        MessageResponse updateBook = bookService.updateBook(id, bookDetails);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Integer id) {
        MessageResponse deleteBook = bookService.deleteBook(id);
        return new ResponseEntity<>(deleteBook, HttpStatus.OK);
    }

}
