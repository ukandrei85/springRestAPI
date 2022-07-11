package com.endava.springrestapi.service;

import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.model.User;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final  UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User create(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getUserAccountPassword());
        user.setUserAccountPassword(encodedPassword);
        userRepository.save(user);
        return removePassword(user);
    }

    public List<User> getAll() {
        return removePasswordsFromList(userRepository.findAll());
    }

    public ResponseEntity<User> findById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id=" + id));
        user.setUserAccountPassword(" ");
        return ResponseEntity.ok(removePassword(user));
    }

    public ResponseEntity<User> update(int id, User userDetails) {
        String encodedPassword = this.passwordEncoder.encode(userDetails.getUserAccountPassword());

        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id=" + id));
        userUpdate.setUserName(userDetails.getUserName());
        userUpdate.setUserAddress(userDetails.getUserAddress());
        userUpdate.setUserAccountLogin(userDetails.getUserAccountLogin());
        userUpdate.setUserAccountPassword(encodedPassword);
        userUpdate.setUserAccountEmail(userDetails.getUserAccountEmail());
        userRepository.save(userUpdate);
        return ResponseEntity.ok(removePassword(userUpdate));
    }

    public ResponseEntity<HttpStatus> delete(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id=" + id));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<User> removePasswordsFromList(List<User> users) {
        return users
                .stream()
                .peek(user -> user.setUserAccountPassword("---"))
                .toList();
    }

    public User removePassword(User user) {
        user.setUserAccountPassword("---");
        return user;
    }

}


