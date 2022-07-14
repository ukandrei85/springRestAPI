package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.AuthenticationDto;
import com.endava.springrestapi.data.response.MessageResponse;

public interface AuthenticationService {
    MessageResponse createAuthentication(AuthenticationDto authApi);

}
