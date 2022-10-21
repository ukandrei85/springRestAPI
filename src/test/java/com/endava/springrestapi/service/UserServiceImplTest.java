package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.repository.RoleRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void shouldCreateAndReturnNewUser() {
        when(userRepository.save(user)).thenReturn(user);
        UserDto testUser = userService.createUser(user);
        assertThat(testUser).usingRecursiveComparison().isEqualTo(user);
    }


    @Test
    void shouldGetUserSuccessfully() {
        when(userRepository.findUserByUsername("andrei85")).thenReturn(user);
        UserDto userDto = userService.getUser("andrei85");
        assertThat(userDto).isNotNull().isInstanceOf(UserDto.class);
        assertThat(userDto.getUsername()).isEqualTo("andrei85");
    }

    @Test
    void shouldNotFindUserAndThrowException() {
        assertThatThrownBy(() -> userService.getUser("andrei85")).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldDeleteSuccessfully() {
        when(userRepository.findUserByUsername("andrei85")).thenReturn(user);
        String str = "User deleted successfully";
        MessageResponse messageResponse = userService.deleteUser("andrei85");
        assertThat(messageResponse.getMessage()).isEqualTo(str);
    }

    @Test
    void shouldNotFindUserForDeleteAndThrowException() {
        assertThatThrownBy(() -> userService.deleteUser("andrei85")).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUserById(1);
        assertThat(userDto).isNotNull();
        assertThat(userDto).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    void getAllUser() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<UserDto> userList = userService.getAllUser();
        assertThat(userList).hasSize(1);
        assertThat(userList.get(0)).usingRecursiveComparison().isEqualTo(user);
    }


    @Test
    void saveRole() {
        when(roleRepository.save(role)).thenReturn(role);
        Role testRole = userService.saveRole(role);
        assertThat(testRole).isNotNull();
        assertThat(testRole).usingRecursiveComparison().isEqualTo(role);
    }

    @Test
    void shouldAddRoleSuccessfully() {
        String str="The role is added successfully";
     when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);
     when(roleRepository.findByName(role.getName())).thenReturn(role);
        MessageResponse messageResponse=userService.addRoleToUser(user.getUsername(),role.getName());
     assertThat(messageResponse.getMessage()).isEqualTo(str);
    }
    @Test
    void shouldNotFindRoleForAddAndThrowException() {
        assertThatThrownBy(()->userService.addRoleToUser(user.getUsername(),role.getName())).isInstanceOf(RuntimeException.class);
    }
    private static final User user = User.builder()
            .id(1)
            .firstName("Andrei")
            .active(true)
            .lastName("Leahu")
            .city("Bucuresti")
            .username("andrei85")
            .accountEmail("andrei22@gmail.com")
            .password("1234")
            .roles(new ArrayList<Role>())
            .build();
    private static final Role role = new Role(1, "ROLE_GUEST");
}