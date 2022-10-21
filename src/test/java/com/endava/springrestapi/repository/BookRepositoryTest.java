package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {
@Autowired
private BookRepository bookRepository;
@Autowired
private UserRepository userRepository;



    @BeforeEach
    public  void setup() {
        userRepository.save(user);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    void findByIsNotRented() {
     List<Book> testBooksList=bookRepository.findByIsNotRented();
     assertThat(testBooksList).isNotEmpty();
     assertThat(testBooksList).hasSize(2);
     assertThat(testBooksList).allSatisfy(b->{
            assertThat(b.getIsRented()).isFalse();
        });
    }

    @Test
    void findBooksByTitleOrAuthor() {
        List<Book> testBooksList1=bookRepository.findBooksByTitleOrAuthor("Ion");
        List<Book> testBooksList2=bookRepository.findBooksByTitleOrAuthor("Rebreanu");
        List<Book> testBooksList3=bookRepository.findBooksByTitleOrAuthor("Creanga");
        assertThat(testBooksList1).isNotEmpty().hasSize(1);
        assertThat(testBooksList1).first().isSameAs(book1);
        assertThat(testBooksList2).isNotEmpty().hasSize(1);
        assertThat(testBooksList3).isEmpty();



    }
    private static final User user = User.builder().firstName("Andrei").lastName("Leahu").city("Bucuresti").username("andrei85").accountEmail("andrei22@gmail.com").active(true).password("1234").roles(new ArrayList<Role>()).build();
    private static final Book book1=Book.builder().title("Ion").author("Liviu Rebreanu").isbn(2222222222222L).isRented(false).isReserved(false).owner(user).build();
    private static final  Book book2=Book.builder().title("Baltagul").author("Mihail Sadoveanu").isbn(3333333333333L).isRented(false).isReserved(false).owner(user).build();
    private static final Book book3=Book.builder().title("Batranul si marea").author("Ernest Hemingway").isbn(4444444444444L).isRented(true).isReserved(true).owner(user).build();
}