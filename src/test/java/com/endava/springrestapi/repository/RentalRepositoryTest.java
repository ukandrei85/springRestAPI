package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.Rental;
import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RentalRepositoryTest {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.save(user1);
        userRepository.save(user2);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        rentalRepository.save(rental1);
        rentalRepository.save(rental2);

    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
        rentalRepository.deleteAll();
    }

    @Test
    void findBooksRentedByUserId() {
        List<Rental> rentalList1=rentalRepository.findBooksRentedByUserId(1);
        List<Rental> rentalList2=rentalRepository.findBooksRentedByUserId(2);
        assertThat(rentalList1).isEmpty();
        assertThat(rentalList2).isNotEmpty().hasSize(1);

    }

    @Test
    void findRentedBooksByOwnerId() {
        List<Rental> rentalList1=rentalRepository.findRentedBooksByOwnerId(1);
        List<Rental> rentalList2=rentalRepository.findRentedBooksByOwnerId(2);
        assertThat(rentalList1).isEmpty();
        assertThat(rentalList2).isNotEmpty().hasSize(1);
    }

    private static final User user1 = User.builder().firstName("Andrei").lastName("Leahu").city("Bucuresti").username("andrei85").accountEmail("andrei22@gmail.com").active(true).password("1234").roles(new ArrayList<Role>()).build();
    private static final User user2 = User.builder().firstName("Mihai").lastName("Lupu").city("Iasi").username("miha85").accountEmail("miha2@gmail.com").active(true).password("1234").roles(new ArrayList<Role>()).build();
    private static final Book book1 = Book.builder().title("Ion").author("Liviu Rebreanu").isbn(2222222222222L).isRented(false).isReserved(false).owner(user1).build();
    private static final Book book2 = Book.builder().title("Baltagul").author("Mihail Sadoveanu").isbn(3333333333333L).isRented(true).isReserved(false).owner(user1).build();
    private static final Book book3 = Book.builder().title("Batranul si marea").author("Ernest Hemingway").isbn(4444444444444L).isRented(true).isReserved(true).owner(user2).build();
    private static final Rental rental1 = Rental.builder().user(user1).book(book1).startPeriod(LocalDate.of(2022,8,10))
            .endPeriod(LocalDate.of(2022,10,10)).build();
    private static final Rental rental2 = Rental.builder().user(user2).book(book3).startPeriod(LocalDate.of(2022,8,10))
            .endPeriod(LocalDate.of(2022,8,11)).build();

}