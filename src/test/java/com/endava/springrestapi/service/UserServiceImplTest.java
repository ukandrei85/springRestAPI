package com.endava.springrestapi.service;

import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@SpringBootTest
@TestPropertySource("/application.properties")
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {


    @Test
    void createUser() {

    }

    @Test
    void saveRole() {
    }

    @Test
    void addRoleToUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getAllUser() {
    }

    @Test
    void mapEntityToApi() {
    }
}