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

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> addBook(@RequestBody BookDto bookDto,@PathVariable int userId) {
        MessageResponse message = bookService.createBook(userId,bookDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{book_id}", method = RequestMethod.GET)
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer book_id) {
        BookDto bookDto = bookService.getASingleBook(book_id);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{book_id}", method = RequestMethod.PATCH)
    public ResponseEntity<MessageResponse> updateBook(@PathVariable Integer book_id, @RequestBody BookDto bookDetails) {
        MessageResponse updateBook = bookService.updateBook(book_id, bookDetails);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/{book_id}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> delete(@PathVariable Integer book_id) {
        MessageResponse deleteBook = bookService.deleteBook(book_id);
        return new ResponseEntity<>(deleteBook, HttpStatus.OK);
    }
    @RequestMapping(value = "/not_rented", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAllBooksAbleToRent() {
        List<BookDto> bookList = bookService.getAllBooksAbleToRent();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
   @RequestMapping(value = "/title/author/{search}", method = RequestMethod.GET)
   public ResponseEntity<List<BookDto>> findBooksByTitleOrAuthor(@PathVariable("search") String search) {
       List<BookDto> bookList = bookService.getBooksByTitleOrAuthor(search);
       return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
