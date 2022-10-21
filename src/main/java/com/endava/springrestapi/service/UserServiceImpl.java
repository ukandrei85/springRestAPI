package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.UserDto;
import com.endava.springrestapi.data.entitie.Role;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.repository.RoleRepository;
import com.endava.springrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDto createUser(User user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return mapEntityToApi(user);
    }


    @Override
    public UserDto getUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User " + username + " was not found");
        }
        return mapEntityToApi(user);
    }


    @Override
    @Transactional
    public MessageResponse deleteUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User is not found");
        }
        userRepository.delete(user);
        return new MessageResponse("User deleted successfully");
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Not found user with id:" + userId));
        return mapEntityToApi(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(this::mapEntityToApi).toList();
    }

    public UserDto mapEntityToApi(User user) {
        return new UserDto(user.getFirstName(), user.getLastName(), user.getCity(), user.getUsername(), user.getAccountEmail(), user.getPassword(), user.getRoles());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public MessageResponse addRoleToUser(String username, String roleName) {
        User user = userRepository.findUserByUsername(username);
        Role role = roleRepository.findByName(roleName);
        if (user == null||role==null) {
            throw new ResourceNotFoundException("User or Role is not found");
        }
        user.getRoles().add(role);
        return new MessageResponse("The role is added successfully");

    }


}


