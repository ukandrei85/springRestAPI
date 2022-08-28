package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void itShouldReturnUserWhenExistsUsername() {
        User user = new User(null, "Andrei", "Leahu", "Bucuresti", "andrei85",
                "andrei22@gmail.com", true, "1234", new ArrayList<Role>());
        userRepository.save(user);
        //when
        User testUser = userRepository.findUserByUsername(user.getUsername());
        //then
        assertThat(testUser).isNotNull();

    }

    @Test
    void itShouldBeNullWhenNotExistsUsername() {
        User user = userRepository.findUserByUsername("ion34@gmail.com");
        assertThat(user).isNull();

    }
}