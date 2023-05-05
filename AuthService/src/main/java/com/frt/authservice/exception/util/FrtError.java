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
    USER_NOT_AUTHENTICATED("User should be authenticated to access this data", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND("User couldn't be found with given parameters", HttpStatus.NOT_FOUND),
    USER_NO_CHANGES_DETECTED("User is already in desired state", HttpStatus.CONFLICT),
    NOTHING_TO_UPDATE("No changed fields detected", HttpStatus.BAD_REQUEST),
    BAD_PASSWORD("Password must be between 8 and 20 characters long and contain at least one digit, lowercase letter, uppercase letter, and special character.", HttpStatus.BAD_REQUEST),
    BAD_PHONE_NUMBER("Phone must be between exactly 9 only digits starting with number above 0", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND("Role parameter is invalid", HttpStatus.BAD_REQUEST),
    SAME_PASSWORD_UPDATE("Password must be different when updating", HttpStatus.CONFLICT),
    SAME_EMAIL_UPDATE("Email must be different when updating", HttpStatus.CONFLICT),
    USER_ACCESS_DENIED("Response data doesn't belong to this user. Access Denied", HttpStatus.FORBIDDEN);

    private final String description;
    private final HttpStatus status;
}
