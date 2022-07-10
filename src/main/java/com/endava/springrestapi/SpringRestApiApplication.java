package com.endava.springrestapi;

import com.endava.springrestapi.model.Book;
import com.endava.springrestapi.model.User;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringRestApiApplication implements  CommandLineRunner{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringRestApiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
       User user=new User();
       user.setUserName("Ion Leahu");
       user.setUserAddress("Timisoara");
       user.setUserAccountLogin("leahuIon");
       user.setUserAccountPassword("123456");
       user.setUserAccountEmail("leahu33@gmail.com");
       userRepository.save(user);
       Book book =new Book();
       book.setTitle("Baltagul");
       book.setIsbn(12456789);
       book.setOwner("Mihai Sadoveanu");
       book.setReserved(false);
       book.setRented(false);
       bookRepository.save(book);
    }
}
