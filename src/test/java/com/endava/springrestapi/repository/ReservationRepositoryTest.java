package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.Reservation;
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
class ReservationRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @BeforeEach
    public void setup() {
        userRepository.save(user1);
        userRepository.save(user2);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        reservationRepository.save(res1);

    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
        reservationRepository.deleteAll();
    }
    @Test
    void findReservationByBookId() {
        List<Reservation> reservationList=reservationRepository.findReservationByBookId(1);
        List<Reservation> reservationList2=reservationRepository.findReservationByBookId(2);
        assertThat(reservationList).hasSize(1).isNotEmpty();
        assertThat(reservationList2).hasSize(0).isEmpty();

    }
    private static final User user1 = User.builder().firstName("Andrei").lastName("Leahu").city("Bucuresti").username("andrei85").accountEmail("andrei22@gmail.com").active(true).password("1234").roles(new ArrayList<Role>()).build();
    private static final User user2 = User.builder().firstName("Mihai").lastName("Lupu").city("Iasi").username("miha85").accountEmail("miha2@gmail.com").active(true).password("1234").roles(new ArrayList<Role>()).build();
    private static final Book book1 = Book.builder().title("Ion").author("Liviu Rebreanu").isbn(2222222222222L).isRented(false).isReserved(false).owner(user1).build();
    private static final Book book2 = Book.builder().title("Baltagul").author("Mihail Sadoveanu").isbn(3333333333333L).isRented(false).isReserved(false).owner(user1).build();
    private static final Book book3 = Book.builder().title("Batranul si marea").author("Ernest Hemingway").isbn(4444444444444L).isRented(true).isReserved(true).owner(user2).build();
    private static final Reservation res1=Reservation.builder().user(user1).book(book1).startDate(LocalDate.of(2022,12,10))
                                                               .endDate(LocalDate.of(2022,12,31)).build();
}