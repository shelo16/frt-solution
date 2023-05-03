package com.frt.authservice.exception.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FrtError {

    INVALID_REQUEST("Invalid Request", HttpStatus.BAD_REQUEST),
    BAD_PASSWORD("Password must be between 8 and 20 characters long and contain at least one digit, lowercase letter, uppercase letter, and special character.", HttpStatus.BAD_REQUEST);

    private final String description;
    private final HttpStatus status;
}
