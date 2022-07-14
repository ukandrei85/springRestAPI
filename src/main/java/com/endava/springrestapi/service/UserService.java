package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.response.MessageResponse;

import java.util.List;

public interface UserService {
    MessageResponse createUser(UserDto userApi);
    MessageResponse  updateUser(Integer userId, UserDto userApi);
    MessageResponse deleteUser(Integer userId);
    UserDto getASingleUser(Integer userId);
    List<UserDto> getAllUser();
}
