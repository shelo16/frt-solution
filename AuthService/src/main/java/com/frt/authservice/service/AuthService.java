package com.frt.authservice.service;

import com.frt.authservice.model.registration.RegistrationRequest;
import com.frt.authservice.model.registration.RegistrationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<RegistrationResponse> register(RegistrationRequest registrationRequest);

}
