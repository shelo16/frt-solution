package com.frt.authservice.controller.auth;

import com.frt.authservice.model.auth.AuthResponse;
import com.frt.authservice.model.auth.authentication.AuthRequest;
import com.frt.authservice.model.auth.registration.RegistrationRequest;
import com.frt.authservice.service.auth.AuthService;
import com.frt.authservice.service.util.UriLocationBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody @NotNull RegistrationRequest registrationRequest) {
        AuthResponse authResponse = authService.register(registrationRequest);
        URI location = UriLocationBuilder.buildUri("/user/{id}", authResponse.getFrtUserId());

        return ResponseEntity
                .created(location)
                .body(authResponse);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody @NotNull AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }


}
