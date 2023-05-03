package com.frt.authservice.service.impl;

import com.frt.authservice.model.RegistrationRequest;
import com.frt.authservice.model.RegistrationResponse;
import com.frt.authservice.persistence.entity.FrtUser;
import com.frt.authservice.persistence.repository.AuthRepository;
import com.frt.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<RegistrationResponse> register(RegistrationRequest registrationRequest) {

        // TODO : improve logic.
        if (registrationRequest.getEmail() == null) {
            return ResponseEntity.badRequest().body(new RegistrationResponse("Email cannot be null"));
        }

        FrtUser frtUser = FrtUser.builder()
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
        FrtUser savedUser = authRepository.save(frtUser);

        // TODO : move to util and generalise
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/{id}")
                .buildAndExpand(savedUser.getUserId()).toUri();

        return ResponseEntity.created(location)
                .body(new RegistrationResponse("User successfully created"));
    }
}
