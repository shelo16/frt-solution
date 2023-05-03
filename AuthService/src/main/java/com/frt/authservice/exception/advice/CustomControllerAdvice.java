package com.frt.authservice.exception.advice;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.model.customexception.GeneralExceptionResponse;
import com.frt.authservice.exception.util.FrtError;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> handleCustomException(GeneralException e) {
        log.error(e.getFrtError().getDescription());
        return new ResponseEntity<>(new GeneralExceptionResponse(List.of(e.getFrtError().getDescription())), e.getFrtError().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralExceptionResponse> handleMethodArgumentException(MethodArgumentNotValidException e) {
        log.error("Error in validating method arguments");
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new GeneralExceptionResponse(errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GeneralExceptionResponse> handleAuthenticationError(BadCredentialsException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new GeneralExceptionResponse(List.of(FrtError.BAD_CREDENTIALS.getDescription())), FrtError.BAD_CREDENTIALS.getStatus());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<GeneralExceptionResponse> handleAuthenticationError(JwtException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new GeneralExceptionResponse(List.of(FrtError.BAD_CREDENTIALS.getDescription())), FrtError.BAD_CREDENTIALS.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e) {
        return new ResponseEntity<>(new GeneralExceptionResponse(List.of(e.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
