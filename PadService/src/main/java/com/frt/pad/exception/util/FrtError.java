package com.frt.pad.exception.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FrtError {

    INVALID_REQUEST("Invalid Request", HttpStatus.BAD_REQUEST),
    BAD_CREDENTIALS("Email or password incorrect", HttpStatus.FORBIDDEN),
    USER_SESSION_EXPIRED("Session expired, please re-login", HttpStatus.UNAUTHORIZED),
    USER_NOT_AUTHENTICATED("User should be authenticated to access this data", HttpStatus.FORBIDDEN),
    ROLE_NOT_FOUND("Role parameter is invalid", HttpStatus.BAD_REQUEST),
    USER_ACCESS_DENIED("Response data doesn't belong to this user. Access Denied", HttpStatus.FORBIDDEN),
    NO_ORDER_WITH_GIVEN_PARAMETERS("Can not find order with give parameters", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("User with given parameters is not found", HttpStatus.BAD_REQUEST),
    ORDER_DELIVERY_NOT_FOUND("Order Delivery with requested parameters was not found", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND("Order with requested parameters was not found", HttpStatus.BAD_REQUEST),
    ORDER_DELIVERY_STATUS_NOT_CHANGED("Order Delivery is already in desired state", HttpStatus.BAD_REQUEST);

    private final String description;
    private final HttpStatus status;
}
