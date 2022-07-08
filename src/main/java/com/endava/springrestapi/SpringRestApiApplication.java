package com.endava.springrestapi;

import com.endava.springrestapi.model.User;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestApiApplication implements  CommandLineRunner{

    @Autowired
    private UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(SpringRestApiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
       User user=new User();
       user.setUserName("Ion");
       user.setUserAddress("Timisoara");
       user.setUserAccountLogin("leahu");
       userRepository.save(user);
    }
}
