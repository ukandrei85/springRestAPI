package com.endava.springrestapi;

import com.endava.springrestapi.model.Book;
import com.endava.springrestapi.model.User;
import com.endava.springrestapi.model.UserBookList;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.UserBooksRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Arrays;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringRestApiApplication implements  CommandLineRunner{
    @Autowired
    private UserBooksRepository userBooksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringRestApiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
