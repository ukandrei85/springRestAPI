package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.BookDto;
import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.entities.User;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.data.entities.Book;
import com.endava.springrestapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private  BookRepository bookRepository;



    @Override
    public MessageResponse createBook(BookDto bookDto) {
        Book newBook=new Book();
        newBook.setTitle(bookDto.getTitle());
        newBook.setAuthor(bookDto.getAuthor());
        newBook.setIsbn(bookDto.getIsbn());
        newBook.setUserId(bookDto.getUserId());
        newBook.setIsRented(bookDto.getIsRented());
        newBook.setIsReserved(bookDto.getIsReserved());
        bookRepository.save(newBook);
        return new MessageResponse("The book has created successfully");
    }

    @Override
    public MessageResponse updateBook(Integer bookId, BookDto bookDto) {
        Book updatedBook=bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Not found book with id:"+bookId));
        updatedBook.setTitle(bookDto.getTitle());
        updatedBook.setAuthor(bookDto.getAuthor());
        updatedBook.setIsbn(bookDto.getIsbn());
        updatedBook.setUserId(bookDto.getUserId());
        updatedBook.setIsRented(bookDto.getIsRented());
        updatedBook.setIsReserved(bookDto.getIsReserved());
        bookRepository.save(updatedBook);
        return new MessageResponse("The book has updated successfully");
    }

    @Override
    public MessageResponse deleteBook(Integer bookId) throws ResourceNotFoundException {
        Book deletedBook=bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Not found book with id:"+bookId));
        bookRepository.delete(deletedBook);
        return new MessageResponse("The book has deleted successfully");
    }

    @Override
    public BookDto getASingleBook(Integer bookId) throws ResourceNotFoundException  {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Not found book with id:"+bookId));
        return mapBookEntityToApi(book);
    }


    @Override
    public List<BookDto> getAllBooks()  {
        return bookRepository.findAll().stream().map(this::mapBookEntityToApi).toList();
    }

    public BookDto  mapBookEntityToApi(Book book){
        return new BookDto(book.getTitle(),book.getAuthor(),book.getIsbn(),book.getUserId(),book.getIsRented(),book.getIsReserved());
    }




}
