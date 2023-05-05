package com.frt.authservice.service.impl.auth;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import com.frt.authservice.model.auth.AuthResponse;
import com.frt.authservice.model.auth.authentication.AuthRequest;
import com.frt.authservice.model.auth.registration.RegistrationRequest;
import com.frt.authservice.persistence.entity.FrtUser;
import com.frt.authservice.persistence.entity.FrtUserDetails;
import com.frt.authservice.persistence.repository.FrtUserRepository;
import com.frt.authservice.service.auth.AuthService;
import com.frt.authservice.service.util.FrtSuccess;
import com.frt.authservice.service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final FrtUserRepository frtUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegistrationRequest registrationRequest) {

        log.info("Validating if user doesn't exit");
        frtUserRepository.findByEmail(registrationRequest.getEmail())
                .ifPresent(user -> {
                    throw new GeneralException(FrtError.USER_ALREADY_EXISTS);
                });

        FrtUser frtUser = buildUser(registrationRequest);
        FrtUserDetails frtUserDetails = buildUserDetails(registrationRequest, frtUser);
        frtUser.setFrtUserDetails(frtUserDetails);
        FrtUser savedUser = frtUserRepository.save(buildUser(registrationRequest));

        var token = jwtUtil.generateToken(savedUser);

        log.info("Created User : " + frtUser.getUserId());
        return AuthResponse.builder()
                .token(token)
                .message(FrtSuccess.OK.getDescription())
                .frtUserId(savedUser.getUserId())
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        log.info("Authenticating user ...");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword())
        );

        var user = frtUserRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(FrtError.INVALID_REQUEST.getDescription()));

        var token = jwtUtil.generateToken(user);

        log.info("User successfully authenticated");
        return AuthResponse.builder()
                .token(token)
                .message(FrtSuccess.OK.getDescription())
                .frtUserId(user.getUserId())
                .build();
    }

    private FrtUser buildUser(RegistrationRequest registrationRequest) {
        return FrtUser.builder()
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
    }

    private FrtUserDetails buildUserDetails(RegistrationRequest registrationRequest, FrtUser frtUser) {
        return FrtUserDetails.builder()
                .frtUser(frtUser)
                .address(registrationRequest.getAddress())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .build();
    }
}
