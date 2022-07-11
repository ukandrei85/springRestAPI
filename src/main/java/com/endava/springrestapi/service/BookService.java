package com.endava.springrestapi.service;

import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.model.Book;
import com.endava.springrestapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }


    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public ResponseEntity<Book> findById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id=" + id));
        return ResponseEntity.ok(book);
    }

    public ResponseEntity<Book> update(int id, Book bookDetails) {
        Book bookUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id=" + id));
        bookUpdate.setTitle(bookDetails.getTitle());
        bookUpdate.setIsbn(bookDetails.getIsbn());
        bookUpdate.setOwner(bookDetails.getOwner());
        bookUpdate.setRented(bookDetails.isRented());
        bookUpdate.setReserved(bookDetails.isReserved());
        bookRepository.save(bookUpdate);
        return ResponseEntity.ok(bookUpdate);
    }

    public ResponseEntity<HttpStatus> delete(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not exist with id=" + id));
        bookRepository.delete(book);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    public List<Book> getAllAbleToRent() {
        return bookRepository.findByIsRented();
    }

}
