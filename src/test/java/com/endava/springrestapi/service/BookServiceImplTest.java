package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.BookDto;
import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void shouldCreateBookSuccessfully() {
        String str = "The book has created successfully";
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        MessageResponse messageResponse = bookService.createBook(1, bookService.mapBookEntityToApi(book));
        assertThat(messageResponse.getMessage()).isEqualTo(str);
    }

    @Test
    void shouldNotFindUserOrBookForAddAndThrowException() {
        assertThatThrownBy(() -> bookService.createBook(1, bookService.mapBookEntityToApi(book))).isExactlyInstanceOf(ResourceNotFoundException.class);
        assertThatThrownBy(() -> bookService.createBook(1, bookService.mapBookEntityToApi(book))).hasMessage("User is not found");

    }

    @Test
    void shouldUpdateBookSuccessfully() {
        String str = "The book has updated successfully";
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        MessageResponse messageResponse = bookService.updateBook(1, bookService.mapBookEntityToApi(book2));
        assertThat(messageResponse.getMessage()).isEqualTo(str);
        assertThat(book.getAuthor()).isEqualTo(book2.getAuthor());

    }

    @Test
    void shouldDeleteSuccessfully() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        String str = "The book has deleted successfully";
        MessageResponse messageResponse = bookService.deleteBook(1);
        assertThat(messageResponse.getMessage()).isEqualTo(str);
    }

    @Test
    void shouldNotFindBookForDeleteAndThrowException() {
        assertThatThrownBy(() -> {
            bookService.deleteBook(1);
        }).isExactlyInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldReturnASingleBook() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getASingleBook(1);
        assertThat(bookDto.getAuthor()).isEqualTo(book.getAuthor());
        assertThat((bookDto.getTitle())).isEqualTo(book.getTitle());
    }

    @Test
    void shouldNotFindASingleBookAndThrowException() {
        String str = "Not found book with id:" + book.getId();
        assertThatThrownBy(() -> bookService.getASingleBook(1)).isInstanceOf(ResourceNotFoundException.class).hasMessage(str);
    }

    @Test
    void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(book));
        List<BookDto> bookDtoList = bookService.getAllBooks();
        assertThat(bookDtoList).hasSize(1);
        assertThat(bookDtoList.get(0).getTitle()).isEqualTo("Ion");
    }

    @Test
    void getAllBooksAbleToRent() {
     when(bookRepository.findByIsNotRented()).thenReturn(List.of(book));
     List<BookDto>bookDtoList =bookService.getAllBooksAbleToRent();
     assertThat(bookDtoList).hasSize(1).filteredOn(bookDto -> bookDto.getIsRented().equals(false));



    }

    @Test
    void getBooksByTitleOrAuthor() {
        when(bookRepository.findBooksByTitleOrAuthor("eanu")).thenReturn(List.of(book,book2));
        List<BookDto>bookDtoList=bookService.getBooksByTitleOrAuthor("eanu");
        assertThat(bookDtoList).allMatch(bookDto -> bookDto.getAuthor().contains("eanu")||bookDto.getTitle().contains("eanu"));

    }

    private static final User user = User.builder()
            .id(1)
            .firstName("Andrei")
            .active(true)
            .lastName("Leahu")
            .city("Bucuresti")
            .username("andrei85")
            .accountEmail("andrei22@gmail.com")
            .password("1234")
            .roles(new ArrayList<Role>())
            .build();
    private static final Book book = Book.builder().id(1).title("Ion").author("Liviu Rebreanu").isbn(2222222222222L).isRented(false).isReserved(false).owner(user).build();
    private static final Book book2 = Book.builder().title("Baltagul").author("Mihail Sadoveanu").isbn(3333333333333L).isRented(true).isReserved(false).owner(user).build();
}