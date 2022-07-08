package com.endava.springrestapi.service;

import com.endava.springrestapi.repository.UserRepository;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public ResponseEntity<User> findById(int id){
        User user= userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not exist with id="+id));
        return ResponseEntity.ok(user);
    }
    public User create(User user){
        return userRepository.save(user);
    }

    public ResponseEntity<User> update(int id,User userDetails){
        User userUpdate=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not exist with id="+id));
        userUpdate.setUserName(userDetails.getUserName());
        userUpdate.setUserAddress(userDetails.getUserAddress());
        userRepository.save(userUpdate);
        return ResponseEntity.ok(userUpdate);
    }
}
