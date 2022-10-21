package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.response.MessageResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(User user);

    Role saveRole(Role role);

    MessageResponse addRoleToUser(String username, String roleName);

    UserDto getUser(String username);

    MessageResponse deleteUser(String username);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUser();
}
