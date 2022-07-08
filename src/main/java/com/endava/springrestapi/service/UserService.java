package com.endava.springrestapi.service;

import com.endava.springrestapi.repository.UserRepository;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User create(User user){
        String encodedPassword=this.passwordEncoder.encode(user.getUserAccountPassword());
        user.setUserAccountPassword(encodedPassword);
        return userRepository.save(user);
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public ResponseEntity<User> findById(int id){
        User user= userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not exist with id="+id));
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<User> update(int id,User userDetails){
        User userUpdate=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not exist with id="+id));
        userUpdate.setUserName(userDetails.getUserName());
        userUpdate.setUserAddress(userDetails.getUserAddress());
        userUpdate.setUserAccountLogin(userDetails.getUserAccountLogin());
        userUpdate.setUserAccountPassword(userDetails.getUserAccountPassword());
        userUpdate.setUserAccountEmail(userDetails.getUserAccountEmail());
        userRepository.save(userUpdate);
        return ResponseEntity.ok(userUpdate);
    }
    public ResponseEntity<HttpStatus> delete(int id ){
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not exist with id="+id));
        userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
