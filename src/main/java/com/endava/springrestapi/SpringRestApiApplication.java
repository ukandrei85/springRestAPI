package com.endava.springrestapi;

import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@SpringBootApplication
@EnableScheduling
public class SpringRestApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringRestApiApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
   @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.createUser(new User(null,"Ion","Turcanu", "Bucuresti","ion58","ion27@gmail.com",true,"1234567",new ArrayList<>()));
            userService.createUser(new User(null,"Andrei","Boaghe", "Bucuresti","andrei58","andrei27@gmail.com",true,"1234567",new ArrayList<>()));
            userService.addRoleToUser("ion58","ROLE_USER");
            userService.addRoleToUser("andrei58","ROLE_ADMIN");
        };
    }


}
