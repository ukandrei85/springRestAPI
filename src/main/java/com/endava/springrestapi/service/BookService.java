package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.BookDto;
import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.response.MessageResponse;

import java.util.List;

public interface BookService {
    MessageResponse createBook(Integer userId,BookDto bookDto);
    MessageResponse  updateBook(Integer bookId, BookDto bookDto);
    MessageResponse deleteBook(Integer bookId);
    BookDto getASingleBook(Integer bookId);
    List<BookDto> getAllBooks();
}
