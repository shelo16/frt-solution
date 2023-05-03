package com.frt.authservice.service.auth;

import com.frt.authservice.model.auth.AuthResponse;
import com.frt.authservice.model.auth.authentication.AuthRequest;
import com.frt.authservice.model.auth.registration.RegistrationRequest;

public interface AuthService {

    AuthResponse register(RegistrationRequest registrationRequest);

    AuthResponse authenticate(AuthRequest authRequest);

}
