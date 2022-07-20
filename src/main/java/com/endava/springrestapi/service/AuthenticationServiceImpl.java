package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.AuthenticationDto;
import com.endava.springrestapi.data.entitie.Authentication;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.repository.AuthenticationRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    public AuthenticationServiceImpl(AuthenticationRepository authRepository) {
        this.authRepository = authRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    private final AuthenticationRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MessageResponse createAuthentication(AuthenticationDto authApi) {
        String encodedPassword = this.passwordEncoder.encode(authApi.getPassword());
        Authentication auth=new Authentication();
        auth.setUserId(authApi.getUserId());
        auth.setPassword(encodedPassword);
        authRepository.save(auth);
        return new MessageResponse("User created successfully");
    }


}
