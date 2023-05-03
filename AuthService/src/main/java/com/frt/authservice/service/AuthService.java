package com.frt.authservice.service;

import com.frt.authservice.model.authentication.AuthRequest;
import com.frt.authservice.model.registration.RegistrationRequest;
import com.frt.authservice.model.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<AuthResponse> register(RegistrationRequest registrationRequest);
    ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest);

}
