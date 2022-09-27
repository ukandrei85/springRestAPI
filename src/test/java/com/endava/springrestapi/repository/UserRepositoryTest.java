package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void findUserByUsername() {
        User testUser1 = userRepository.findUserByUsername("andrei85");
        User testUser2 = userRepository.findUserByUsername("ion34@gmail.com");
        assertThat(testUser1.getId()).isGreaterThan(0);
        assertThat(testUser1).isNotNull();
        assertThat(testUser2).isNull();
    }

    private static final User user = User.builder()
            .firstName("Andrei")
            .lastName("Leahu")
            .city("Bucuresti")
            .username("andrei85")
            .accountEmail("andrei22@gmail.com")
            .active(true)
            .password("1234")
            .roles(new ArrayList<Role>())
            .build();
}