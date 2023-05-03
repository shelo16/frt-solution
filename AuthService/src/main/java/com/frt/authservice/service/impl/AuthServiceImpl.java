package com.frt.authservice.service.impl;

import com.frt.authservice.model.registration.RegistrationRequest;
import com.frt.authservice.model.registration.RegistrationResponse;
import com.frt.authservice.persistence.entity.FrtUser;
import com.frt.authservice.persistence.repository.AuthRepository;
import com.frt.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    public ResponseEntity<RegistrationResponse> register(RegistrationRequest registrationRequest) {

        FrtUser frtUser = new FrtUser();
        BeanUtils.copyProperties(registrationRequest, frtUser);

        FrtUser savedUser = authRepository.save(frtUser);
        // TODO : move to util and generalise
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/{id}")
                .buildAndExpand(savedUser.getUserId()).toUri();

        return ResponseEntity.created(location)
                .body(new RegistrationResponse("User successfully created"));
    }
}
