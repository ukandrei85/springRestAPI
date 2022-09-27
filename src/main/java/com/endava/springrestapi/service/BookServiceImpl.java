package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.BookDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private  BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentalService rentalService;


    @Override
    @Transactional
    public MessageResponse createBook(Integer userId,BookDto bookDto) {
        Book newBook=new Book();
        newBook.setOwner(userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not found")));
        newBook.setTitle(bookDto.getTitle());
        newBook.setAuthor(bookDto.getAuthor());
        newBook.setIsbn(bookDto.getIsbn());
        newBook.setIsRented(bookDto.getIsRented());
        newBook.setIsReserved(bookDto.getIsReserved());
        bookRepository.save(newBook);
        return new MessageResponse("The book has created successfully");
    }

    @Override
    @Transactional
    public MessageResponse updateBook(Integer bookId, BookDto bookDto) {
        Book updatedBook=bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Not found book with id:"+bookId));
        updatedBook.setTitle(bookDto.getTitle());
        updatedBook.setAuthor(bookDto.getAuthor());
        updatedBook.setIsbn(bookDto.getIsbn());

        updatedBook.setIsRented(bookDto.getIsRented());
        updatedBook.setIsReserved(bookDto.getIsReserved());
        bookRepository.save(updatedBook);
        return new MessageResponse("The book has updated successfully");
    }

    @Override
    @Transactional
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
        return new BookDto(book.getTitle(),book.getAuthor(),book.getIsbn(),book.getOwner().getId(),book.getIsRented(),book.getIsReserved(),book.getEndRentPeriod());
    }

    public List<BookDto> getAllBooksAbleToRent(){
        return bookRepository.findByIsNotRented().stream().map(this::mapBookEntityToApi).toList();
    }
   public List<BookDto> getBooksByTitleOrAuthor(String search){
        return bookRepository.findBooksByTitleOrAuthor(search).stream().map(this::mapBookEntityToApi).toList();
    }


}
