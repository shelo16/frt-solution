package com.frt.authservice.exception.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FrtError {

    INVALID_REQUEST("Invalid Request", HttpStatus.BAD_REQUEST),
    BAD_CREDENTIALS("Email or password incorrect", HttpStatus.FORBIDDEN),
    USER_ALREADY_EXISTS("User with this email already exists", HttpStatus.BAD_REQUEST),
    USER_SESSION_EXPIRED("Session expired, please re-login", HttpStatus.UNAUTHORIZED),
    BAD_PASSWORD("Password must be between 8 and 20 characters long and contain at least one digit, lowercase letter, uppercase letter, and special character.", HttpStatus.BAD_REQUEST);

    private final String description;
    private final HttpStatus status;
}
