package com.frt.authservice.service;

import com.frt.authservice.model.RegistrationRequest;
import com.frt.authservice.model.RegistrationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<RegistrationResponse> register(RegistrationRequest registrationRequest);

}
