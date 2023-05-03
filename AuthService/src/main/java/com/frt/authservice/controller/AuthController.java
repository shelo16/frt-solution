package com.frt.authservice.controller;

import com.frt.authservice.model.registration.RegistrationRequest;
import com.frt.authservice.model.registration.RegistrationResponse;
import com.frt.authservice.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody @NotNull RegistrationRequest registrationRequest) {
        return authService.register(registrationRequest);
    }


}
