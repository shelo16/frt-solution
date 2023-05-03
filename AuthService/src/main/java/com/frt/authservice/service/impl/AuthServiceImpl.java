package com.frt.authservice.service.impl;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import com.frt.authservice.model.AuthResponse;
import com.frt.authservice.model.authentication.AuthRequest;
import com.frt.authservice.model.registration.RegistrationRequest;
import com.frt.authservice.persistence.entity.FrtUser;
import com.frt.authservice.persistence.repository.FrtUserRepository;
import com.frt.authservice.service.AuthService;
import com.frt.authservice.service.util.FrtSuccess;
import com.frt.authservice.service.util.JwtUtil;
import com.frt.authservice.service.util.UriLocationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final FrtUserRepository frtUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<AuthResponse> register(RegistrationRequest registrationRequest) {

        frtUserRepository.findByEmail(registrationRequest.getEmail())
                .ifPresent(user -> {
                    throw new GeneralException(FrtError.USER_ALREADY_EXISTS);
                });

        FrtUser savedUser = frtUserRepository.save(buildUser(registrationRequest));

        URI location = UriLocationBuilder.buildUri("/user/{id}", savedUser.getUserId());

        var token = jwtUtil.generateToken(savedUser);

        return ResponseEntity
                .created(location)
                .body(new AuthResponse(
                        FrtSuccess.CREATED.getDescription(),
                        token)
                );
    }

    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword())
        );

        var user = frtUserRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(FrtError.INVALID_REQUEST.getDescription()));
        var jwtToken = jwtUtil.generateToken(user);
        return ResponseEntity
                .ok(AuthResponse.builder()
                        .token(jwtToken)
                        .message(FrtSuccess.OK.getDescription())
                        .build());
    }

    private FrtUser buildUser(RegistrationRequest registrationRequest) {
        return FrtUser.builder()
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
    }
}
