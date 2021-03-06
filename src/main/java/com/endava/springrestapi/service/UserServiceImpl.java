package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public MessageResponse createUser(UserDto userApi) {
        User user=new User();
        user.setFirstName(userApi.getFirstName());
        user.setLastName(userApi.getLastName());
        user.setAccountEmail(userApi.getAccountEmail());
        user.setAccountLogin(userApi.getAccountLogin());
        user.setCity(userApi.getCity());
        userRepository.save(user);
        return new MessageResponse("User created successfully");
    }

    @Override
    @Transactional
    public MessageResponse  updateUser(Integer userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Not found user with id:"+userId));
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setCity(userDto.getCity());
            user.setAccountLogin(userDto.getAccountLogin());
            user.setAccountEmail(userDto.getAccountEmail());
            userRepository.save(user);
            return  new MessageResponse("User updated successfully");

    }

    @Override
    @Transactional
    public MessageResponse deleteUser(Integer userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id=" + userId));
        userRepository.delete(user);
        return new MessageResponse("User deleted successfully");
    }

    @Override
    public UserDto getASingleUser(Integer userId)throws ResourceNotFoundException {
        User user= userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("Not found user with id:"+userId));
        return mapEntityToApi(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(this::mapEntityToApi).toList();
    }

    public UserDto mapEntityToApi(User user){
        return new UserDto(user.getFirstName(), user.getLastName(), user.getCity(), user.getAccountLogin(), user.getAccountEmail(), null);
    }

}


