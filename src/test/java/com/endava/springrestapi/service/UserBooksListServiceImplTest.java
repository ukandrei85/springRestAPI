package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserBookListDto;
import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.entitie.UserBookList;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.UserBooksRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserBooksListServiceImplTest {
    @Mock
    private UserBooksRepository userBooksRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private UserBooksListServiceImpl userBooksListService;

    @Test
    void createUserBookList() {
        String str = "UserBooksList created successfully";
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        MessageResponse messageResponse = userBooksListService.createUserBookList(userBooksListService.mapEntityToApi(userBookList));
        assertThat(messageResponse.getMessage()).isEqualTo(str);
    }

    @Test
    void updateUserBookList() {
        String str = "UserBooksList update successfully";
        when(userBooksRepository.findById(1)).thenReturn(Optional.of(userBookList));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        MessageResponse messageResponse =userBooksListService.updateUserBookList(1,userBooksListService.mapEntityToApi(userBookList));
        assertThat(messageResponse.getMessage()).isEqualTo(str);
    }

    @Test
    void deleteUserBookList() {
        String str="UserBooksList deleted successfully";
        when(userBooksRepository.findById(1)).thenReturn(Optional.of(userBookList));
        MessageResponse messageResponse=userBooksListService.deleteUserBookList(1);
        assertThat(messageResponse.getMessage()).isEqualTo(str);
    }

    @Test
    void getASingleUserBookList() {
        when(userBooksRepository.findById(1)).thenReturn(Optional.of(userBookList));
        UserBookListDto userBookListDto=userBooksListService.getASingleUserBookList(1);
        assertThat(userBookListDto.getUserId()).isEqualTo(userBookList.getUser().getId());
        assertThat(userBookListDto.getBookId()).isEqualTo(userBookList.getBook().getId());
    }

    @Test
    void getAllUserBookList() {
     when(userBooksRepository.findAll()).thenReturn(List.of(userBookList));
     List<UserBookListDto> userBookListDtoList=userBooksListService.getAllUserBookList();
     List<UserBookListDto> userBookListDtoListTest=List.of(userBookDto);
     assertThat(userBookListDtoList).usingRecursiveComparison().isEqualTo(userBookListDtoListTest);
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
    private static final UserBookList userBookList = UserBookList.builder().userBookId(1).user(user).book(book).build();
    private  static final UserBookListDto userBookDto=new UserBookListDto(1,1);
}