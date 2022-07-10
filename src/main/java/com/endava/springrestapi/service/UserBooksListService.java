package com.endava.springrestapi.service;

import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.model.Book;
import com.endava.springrestapi.model.User;
import com.endava.springrestapi.model.UserBookList;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.UserBooksRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBooksListService {
    @Autowired
    private UserBooksRepository userBooksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public UserBookList create(int userId, int bookId){
        UserBookList userBookList=new UserBookList(userRepository.getReferenceById(userId),
                bookRepository.getReferenceById(bookId));
        return userBooksRepository.save(userBookList);
    }
    public List<UserBookList> getAll() {
        return userBooksRepository.findAll();
    }

    public ResponseEntity<UserBookList> findById(int id) {
        UserBookList userBookList = userBooksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id=" + id));
        return ResponseEntity.ok(userBookList);
    }
    public ResponseEntity<HttpStatus> delete(int id) {
        UserBookList userBookList = userBooksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id=" + id));
        userBooksRepository.delete(userBookList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
